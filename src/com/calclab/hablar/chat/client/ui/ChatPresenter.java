package com.calclab.hablar.chat.client.ui;

import static com.calclab.hablar.chat.client.HablarChat.i18n;

import java.util.ArrayList;

import com.calclab.emite.core.client.packet.TextUtils;
import com.calclab.emite.core.client.xmpp.stanzas.Message;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.emite.im.client.chat.Chat.State;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.Idify;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.page.events.UserMessageEvent;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.PresenceIcon;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;

public class ChatPresenter extends PagePresenter<ChatDisplay> implements ChatPage {
    public static final String TYPE = "Chat";
    public static final String CHAT_MESSAGE = "ChatMessage";

    public static ChatPresenter asChat(final Page<?> page) {
	if (isChat(page)) {
	    return (ChatPresenter) page;
	}
	return null;
    }

    public static boolean isChat(final Page<?> page) {
	return ChatPresenter.TYPE.equals(page.getType());
    }

    private final Chat chat;
    private final String userName;

    public ChatPresenter(final HablarEventBus eventBus, final Chat chat, final ChatDisplay display) {
	super(TYPE, Idify.uriId(chat.getURI().toString()), eventBus, display);
	this.chat = chat;
	display.setId(getId());
	final XmppURI fromURI = chat.getURI();
	userName = getName(fromURI);

	model.init(HablarIcons.get(IconType.buddyOff), userName, userName + ": " + fromURI.toString());
	setVisibility(Visibility.notFocused);
	model.setCloseable(true);

	chat.onMessageReceived(new Listener<Message>() {
	    @Override
	    public void onEvent(final Message message) {
		final String body = ChatMessageFormatter.format(message.getBody());
		if (body != null) {
		    display.addMessage(userName, body, ChatDisplay.MessageType.incoming);
		    fireUserMessage(body);
		    if (getVisibility() == Visibility.hidden) {
			requestVisibility(Visibility.notFocused);
		    }
		}
	    }

	});
	chat.onStateChanged(new Listener<State>() {
	    @Override
	    public void onEvent(final State state) {
		setState(state);
	    }
	});
	setState(chat.getState());

	display.getAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		sendMessage(chat, display);
	    }

	});
	display.getTextBox().addKeyDownHandler(new KeyDownHandler() {
	    @Override
	    public void onKeyDown(final KeyDownEvent event) {
		if (event.getNativeKeyCode() == 13) {
		    event.stopPropagation();
		    event.preventDefault();
		    sendMessage(chat, display);
		}
	    }
	});
    }

    @Override
    public void addAction(final Action<ChatPage> action) {
	display.createAction(action).addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		action.execute(ChatPresenter.this);
	    }
	});
    }

    @Override
    public Chat getChat() {
	return chat;
    }

    @Override
    public String getChatName() {
	return userName;
    }

    @Override
    public ArrayList<ChatMessageDisplay> getMessages() {
	return display.getMessages();
    }

    @Override
    public void setPresence(final boolean available, final Show show) {
	getState().setPageIcon(PresenceIcon.getIcon(available, show));
	if (available) {
	    display.getState().setText(i18n().stateAvailable(userName));
	} else {
	    display.getState().setText(i18n().stateOffline(userName));
	}
    }

    private void fireUserMessage(final String body) {
	final String message = i18n().newChatFrom(userName, TextUtils.ellipsis(body, 25));
	eventBus.fireEvent(new UserMessageEvent(this, message, CHAT_MESSAGE));
    }

    private String getName(final XmppURI fromURI) {
	final String name;
	final Roster roster = Suco.get(Roster.class);
	final RosterItem itemByJID = roster.getItemByJID(fromURI);
	if (itemByJID != null) {
	    name = itemByJID.getName();
	} else {
	    name = fromURI.getShortName();
	}
	return name;
    }

    private void sendMessage(final Chat chat, final ChatDisplay display) {
	final String text = display.getBody().getText().trim();
	if (!text.isEmpty()) {
	    final String body = ChatMessageFormatter.format(text);
	    display.addMessage("me", body, ChatDisplay.MessageType.sent);
	    chat.send(new Message(text));
	    display.clearAndFocus();
	}
    }

    private void setState(final State state) {
	final boolean visible = state == State.ready;
	display.setControlsVisible(visible);
	display.setStatusVisible(visible);
    }

}
