package com.calclab.hablar.chat.client;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.AbstractChatManager;
import com.calclab.emite.im.client.chat.Chat;

public class ChatManagerTester extends AbstractChatManager {

    public ChatManagerTester(final Session session) {
	super(session);
    }

    @Override
    public void fireChatClosed(final Chat chat) {
	super.fireChatClosed(chat);
    }

    @Override
    public void fireChatCreated(final Chat chat) {
	super.fireChatCreated(chat);
    }

    @Override
    public void fireChatOpened(final Chat chat) {
	super.fireChatOpened(chat);
    }

    @Override
    public Chat getChat(final XmppURI uri) {
	return null;
    }

    @Override
    protected Chat createChat(final XmppURI uri, final XmppURI currentUser) {
	return new ChatTester(uri, currentUser);
    }
}
