package com.calclab.hablar.client.chat;

import com.calclab.emite.core.client.xmpp.stanzas.Message;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.AbstractChat;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.emite.xtesting.SessionTester;

public class ChatTester extends AbstractChat implements Chat {
    private final String id;

    public ChatTester(String uri, String starter) {
	this(XmppURI.uri(uri), XmppURI.uri(starter));
    }

    public ChatTester(XmppURI uri, XmppURI starter) {
	super(new SessionTester(), uri, starter);
	this.id = "chat" + uri.toString() + System.currentTimeMillis();
    }

    @Override
    public String getID() {
	return id;
    }

    @Override
    public void receive(Message message) {
	super.receive(message);
    }

    @Override
    public void setState(State state) {
	super.setState(state);
    }

}