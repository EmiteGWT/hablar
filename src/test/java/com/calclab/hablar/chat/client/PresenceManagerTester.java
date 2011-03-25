package com.calclab.hablar.chat.client;

import com.calclab.emite.core.client.xmpp.session.SessionReady;
import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.im.client.presence.PresenceManagerImpl;

public class PresenceManagerTester extends PresenceManagerImpl {

	public PresenceManagerTester(final XmppSession session) {
		super(session, new SessionReady(session));
	}

}
