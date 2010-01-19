package com.calclab.hablar.chat.client;

import com.calclab.emite.core.client.xmpp.stanzas.Presence;
import com.calclab.emite.im.client.presence.AbstractPresenceManager;

public class PresenceManagerTester extends AbstractPresenceManager {

    private int times;

    public PresenceManagerTester() {
	setOwnPresence(new Presence());
    }

    @Override
    public void changeOwnPresence(Presence presence) {
	this.times++;
	setOwnPresence(presence);
    }

    public int getChangePresenceCallCount() {
	return times;
    }

}
