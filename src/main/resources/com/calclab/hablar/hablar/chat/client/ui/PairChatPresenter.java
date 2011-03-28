package com.calclab.hablar.chat.client.ui;

import static com.calclab.hablar.chat.client.HablarChat.i18n;

import java.util.Date;

import com.calclab.emite.core.client.events.MessageEvent;
import com.calclab.emite.core.client.events.MessageHandler;
import com.calclab.emite.core.client.packet.TextUtils;
import com.calclab.emite.core.client.xmpp.stanzas.Message;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.core.client.xmpp.stanzas.Message.Type;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.emite.xep.delay.client.Delay;
import com.calclab.emite.xep.delay.client.DelayHelper;
import com.calclab.hablar.core.client.Idify;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.events.UserMessageEvent;
import com.calclab.hablar.core.client.ui.icon.Icons;
import com.calclab.hablar.core.client.ui.icon.PresenceIcon;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.validators.Empty;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;

public class PairChatPresenter extends ChatPresenter implements PairChatPage {
    public static final String TYPE = "Chat";
    public static final String CHAT_MESSAGE = "ChatMessage";

    private final Chat chat;
    private final String userName;

    public PairChatPresenter(final XmppRoster roster, final HablarEventBus eventBus, final Chat chat,
	    final ChatDisplay display) {
	super(TYPE, Idify.uriId(chat.getURI().toString()), eventBus, chat, display);
	this.chat = chat;
	display.setId(getId());
	final XmppURI fromURI = chat.getURI();
	userName = roster.getJidName(fromURI);

	model.init(Icons.BUDDY_OFF, userName, userName + ": " + fromURI.toString());
	setVisibility(Visibility.notFocused);
	model.setCloseable(true);

	chat.addMessageReceivedHandler(new MessageHandler() {

	    @Override
	    public void onMessage(final MessageEvent event) {
		receiveMessage(event.getMessage());
	    }
	});

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
    public void addAction(final Action<PairChatPage> action) {
	display.createAction(action).addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		action.execute(PairChatPresenter.this);
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
    public void setPresence(final boolean available, final Show show) {
	getState().setPageIcon(PresenceIcon.get(available, show));
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

    private void receiveMessage(final Message message) {
	final String messageBody = message.getBody();
	if ((Type.error != message.getType()) && Empty.not(messageBody)) {
	    final Delay delay = DelayHelper.getDelay(message);
	    final ChatMessage chatMessage = new ChatMessage(userName, messageBody, ChatMessage.MessageType.incoming);
	    chatMessage.color = ColorHelper.getColor(message.getFrom().getJID());
	    if (delay != null) {
		chatMessage.setDate(delay.getStamp());
	    } else {
		chatMessage.setDate(new Date());
	    }
	    addMessage(chatMessage);
	    fireUserMessage(messageBody);
	    if (getVisibility() == Visibility.hidden) {
		requestVisibility(Visibility.notFocused);
	    }
	}
    }
}
