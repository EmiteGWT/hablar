package com.calclab.hablar.chat.client.ui;

import static com.calclab.hablar.core.client.i18n.Translator.i18n;

import com.calclab.emite.core.client.xmpp.stanzas.Message;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.emite.im.client.chat.Chat.State;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.PresenceIcon;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.roster.client.RosterPresenter;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;

public class ChatPresenter extends PagePresenter<ChatDisplay> {
    public static final String TYPE = "Chat";

    public static String createId(String uri) {
	return uri.replace("@", "-").replace("/", "-");
    }

    public ChatPresenter(HablarEventBus eventBus, final Chat chat, final ChatDisplay display) {
	super(TYPE, createId(chat.getURI().toString()), eventBus, display);
	display.setId(getId());
	final XmppURI fromURI = chat.getURI();
	final String name = getName(fromURI);

	getState().setPageTitle(name);
	getState().setPageIcon(HablarIcons.get(IconType.buddyOff));
	getState().setCloseable(true);
	chat.onMessageReceived(new Listener<Message>() {
	    @Override
	    public void onEvent(final Message message) {
		final String body = ChatMessageFormatter.format(message.getBody());
		if (body != null) {
		    display.showMessage(name, body, ChatDisplay.MessageType.incoming);
		    getState().setUserMessage(i18n().newChatFrom(name, ChatMessageFormatter.ellipsis(body, 25)));
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
	    public void onClick(ClickEvent event) {
		sendMessage(chat, display);
	    }

	});
	display.getTextBox().addKeyDownHandler(new KeyDownHandler() {
	    @Override
	    public void onKeyDown(KeyDownEvent event) {
		if (event.getNativeKeyCode() == 13) {
		    event.stopPropagation();
		    event.preventDefault();
		    sendMessage(chat, display);
		}
	    }
	});
    }

    /**
     * Add a action button to this chat panel
     * 
     * @see RosterPresenter.addAction
     */
    // FIXME
    public void addAction() {

    }

    public void setPresence(boolean available, Show show) {
	getState().setPageIcon(PresenceIcon.getIcon(available, show));
    }

    private String getName(final XmppURI fromURI) {
	final String name;
	Roster roster = Suco.get(Roster.class);
	RosterItem itemByJID = roster.getItemByJID(fromURI);
	if (itemByJID != null) {
	    name = itemByJID.getName();
	} else {
	    name = fromURI.getShortName();
	}
	return name;
    }

    private void sendMessage(final Chat chat, final ChatDisplay display) {
	String text = display.getBody().getText().trim();
	if (!text.isEmpty()) {
	    final String body = ChatMessageFormatter.format(text);
	    display.showMessage("me", body, ChatDisplay.MessageType.sent);
	    chat.send(new Message(text));
	    display.clearAndFocus();
	}
    }

    private void setState(final State state) {
	final boolean visible = state == State.ready;
	display.setControlsVisible(visible);
    }

}
