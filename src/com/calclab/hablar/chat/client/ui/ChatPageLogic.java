package com.calclab.hablar.chat.client.ui;

import com.calclab.emite.core.client.xmpp.stanzas.Message;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.emite.im.client.chat.Chat.State;
import com.calclab.hablar.basic.client.i18n.Msg;
import com.calclab.hablar.basic.client.ui.icon.HablarIcons;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;

public class ChatPageLogic {
    private final Chat chat;
    private final ChatPageView view;

    public ChatPageLogic(final Chat chat, final ChatPageView view) {
	this.chat = chat;
	this.view = view;
	final Msg i18n = Suco.get(Msg.class);
	final XmppURI fromURI = chat.getURI();
	final String name = fromURI.getShortName();

	view.setHeaderTitle(name);
	chat.onMessageReceived(new Listener<Message>() {
	    @Override
	    public void onEvent(final Message message) {
		final String body = ChatMessageFormatter.format(message.getBody());
		view.showMessage(name, body, ChatPageView.MessageType.incoming);
		view.setStatusMessage(i18n.newChatFrom(name, ChatMessageFormatter.ellipsis(body, 25)));
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
	    view.showMessage("me", body, ChatPageView.MessageType.sent);
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

    private void setState(final State state) {
	final boolean visible = state == State.ready;
	view.setControlsVisible(visible);
    }
}
