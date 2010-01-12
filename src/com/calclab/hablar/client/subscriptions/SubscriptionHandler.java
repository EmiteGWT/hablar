package com.calclab.hablar.client.subscriptions;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.roster.SubscriptionManager;
import com.calclab.suco.client.events.Listener2;
import com.google.gwt.core.client.GWT;

public class SubscriptionHandler {

    public static enum Behaviour {
	none, acceptAll, refuseAll
    }

    private final SubscriptionManager manager;
    private Behaviour behaviour;

    public SubscriptionHandler(final SubscriptionManager manager) {
	this.manager = manager;
	this.behaviour = Behaviour.none;

	manager.onSubscriptionRequested(new Listener2<XmppURI, String>() {
	    @Override
	    public void onEvent(XmppURI uri, String nick) {
		GWT.log("Subscription requested: " + nick, null);
		if (behaviour == Behaviour.acceptAll) {
		    manager.approveSubscriptionRequest(uri, nick);
		} else if (behaviour == Behaviour.refuseAll) {
		    manager.refuseSubscriptionRequest(uri);
		}
	    }
	});
    }

    public void setBehaviour(Behaviour behaviour) {
	this.behaviour = behaviour;
    }

}
