package com.calclab.hablar.chat.client;

import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.im.client.chat.PairChatManager;

public class ChatManagerTester extends PairChatManager {
    public ChatManagerTester(final XmppSession session) {
	super(session);
    }

}
