package com.calclab.hablar.chat.client;

import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.core.client.xmpp.stanzas.Message;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.AbstractChat;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.emite.im.client.chat.ChatProperties;

public class ChatTester extends AbstractChat implements Chat {
    private final String id;

    public ChatTester(final XmppSession session, final ChatProperties chatProperties) {
	super(session, chatProperties);
	id = "chat" + chatProperties.getUri().toString() + System.currentTimeMillis();
    }

    public ChatTester(final XmppSession session, final String uri, final String starter) {
	this(session, new ChatProperties(XmppURI.uri(uri), XmppURI.uri(starter), null));
    }

    @Override
    public String getID() {
	return id;
    }

    @Override
    public void open() {
	setChatState(ChatStates.ready);
    }

    @Override
    public void receive(final Message message) {
	super.receive(message);
    }

    @Override
    public void setState(final State state) {
	super.setState(state);
    }

}