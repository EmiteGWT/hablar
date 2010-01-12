package com.calclab.hablar.client.chat;

import com.calclab.emite.core.client.xmpp.stanzas.Message;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.suco.client.events.Listener;

public class ChatLogic {
    private final Chat chat;
    private final ChatPage widget;

    public ChatLogic(Chat chat, final ChatPage widget) {
	this.chat = chat;
	this.widget = widget;

	final String name = chat.getURI().getNode();
	widget.setHeaderTitle(name);
	chat.onMessageReceived(new Listener<Message>() {
	    @Override
	    public void onEvent(Message message) {
		widget.showMessage(name, message.getBody(), ChatPage.MessageType.incoming);
		widget.setStatus("Incoming message from " + name);
	    }
	});
    }

    public void onTalk(String text) {
	widget.showMessage("me", text, ChatPage.MessageType.sent);
	chat.send(new Message(text));
	widget.clearAndFocus();
    }

}
