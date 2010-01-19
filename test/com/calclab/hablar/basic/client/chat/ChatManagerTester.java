package com.calclab.hablar.basic.client.chat;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.AbstractChatManager;
import com.calclab.emite.im.client.chat.Chat;

public class ChatManagerTester extends AbstractChatManager {

    public ChatManagerTester(Session session) {
	super(session);
    }

    @Override
    public void fireChatClosed(Chat chat) {
	super.fireChatClosed(chat);
    }

    @Override
    public void fireChatCreated(Chat chat) {
	super.fireChatCreated(chat);
    }

    @Override
    public void fireChatOpened(Chat chat) {
	super.fireChatOpened(chat);
    }

    @Override
    protected Chat createChat(XmppURI uri, XmppURI currentUser) {
	return new ChatTester(uri, currentUser);
    }

    @Override
    protected Chat findChat(XmppURI uri) {
	return null;
    }
}
