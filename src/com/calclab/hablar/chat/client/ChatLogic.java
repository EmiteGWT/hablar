package com.calclab.hablar.chat.client;

import com.calclab.emite.core.client.xmpp.stanzas.Message;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.emite.im.client.chat.Chat.State;
import com.calclab.hablar.basic.client.i18n.Msg;
import com.calclab.hablar.basic.client.ui.icon.HablarIcons;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;

public class ChatLogic {
    private final Chat chat;
    private final ChatView view;

    public ChatLogic(final Chat chat, final ChatView view) {
	this.chat = chat;
	this.view = view;
	final Msg i18n = Suco.get(Msg.class);
	final XmppURI fromURI = chat.getURI();
	// FIXME: I think this should be in a emite-lib.XmppURI.getShortName()
	// method and use it in other situations to avoid the same bug
	final String name = fromURI.getNode() == null ? fromURI.getHost() : fromURI.getNode();
	view.setHeaderTitle(name);
	chat.onMessageReceived(new Listener<Message>() {
	    @Override
	    public void onEvent(final Message message) {
		final String body = ChatMessageFormatter.format(message.getBody());
		view.showMessage(name, body, ChatPage.MessageType.incoming);
		view.setStatusMessage(i18n.newChatFrom(name, ellipsis(body, 25)));
	    }
	});
	chat.onStateChanged(new Listener<State>() {
	    @Override
	    public void onEvent(final State state) {
		setState(state);
	    }
	});
	setState(chat.getState());
    }

    public void onTalk(final String text) {
	if (!text.isEmpty()) {
	    final String body = ChatMessageFormatter.format(text);
	    view.showMessage("me", body, ChatPage.MessageType.sent);
	    chat.send(new Message(text));
	    view.clearAndFocus();
	}
    }

    public void setPresence(final Show show) {
	if (show == Show.chat) {
	    view.setHeaderIconClass(HablarIcons.get(HablarIcons.IconType.buddyOn));
	} else if (show == Show.dnd) {
	    view.setHeaderIconClass(HablarIcons.get(HablarIcons.IconType.buddyDnd));
	} else if (show == Show.away) {
	    view.setHeaderIconClass(HablarIcons.get(HablarIcons.IconType.buddyWait));
	} else {
	    view.setHeaderIconClass(HablarIcons.get(HablarIcons.IconType.buddyOff));
	}
    }

    private String ellipsis(final String text, final int length) {
	// FIXME this must go in emite-lib.TextUtils
	return text.length() > length ? text.substring(0, length - 3) + "..." : text;
    }

    private void setState(final State state) {
	final boolean visible = state == State.ready;
	view.setTextBoxVisible(visible);
    }
}
