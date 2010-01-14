package com.calclab.hablar.client.chat;

import com.calclab.emite.core.client.xmpp.stanzas.Message;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.suco.client.events.Listener;

public class ChatLogic {
    private final Chat chat;
    private final ChatView view;

    public ChatLogic(final Chat chat, final ChatView view) {
	this.chat = chat;
	this.view = view;

	final XmppURI fromURI = chat.getURI();
	final String name = fromURI.getNode() == null ? fromURI.getHost() : fromURI.getNode();
	view.setHeaderTitle(name);
	chat.onMessageReceived(new Listener<Message>() {
	    @Override
	    public void onEvent(final Message message) {
		String body = ChatMessageFormatter.format(message.getBody());
		view.showMessage(name, body, ChatPage.MessageType.incoming);
		view.setStatusMessage("Incoming message from " + name);
	    }
	});
    }

    public void onTalk(final String text) {
	if (!text.isEmpty()) {
	    String body = ChatMessageFormatter.format(text);
	    view.showMessage("me", body, ChatPage.MessageType.sent);
	    chat.send(new Message(text));
	    view.clearAndFocus();
	}
    }

}
