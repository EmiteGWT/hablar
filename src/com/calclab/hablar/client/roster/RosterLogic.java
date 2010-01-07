package com.calclab.hablar.client.roster;

import java.util.Collection;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.im.client.roster.SubscriptionManager;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.calclab.suco.client.events.Listener2;

public class RosterLogic {

    private final Roster roster;
    private final ChatManager chatManager;
    private final RosterPage widget;

    public RosterLogic(final RosterPage widget) {
	this.widget = widget;
	this.roster = Suco.get(Roster.class);
	this.chatManager = Suco.get(ChatManager.class);

	roster.onRosterRetrieved(new Listener<Collection<RosterItem>>() {
	    @Override
	    public void onEvent(Collection<RosterItem> items) {
		for (RosterItem item : items) {
		    widget.addItem(new RosterItemWidget(item, RosterLogic.this));
		}
	    }
	});

	final SubscriptionManager subscriptionManager = Suco.get(SubscriptionManager.class);
	subscriptionManager.onSubscriptionRequested(new Listener2<XmppURI, String>() {
	    @Override
	    public void onEvent(final XmppURI uri, final String nickName) {
		String msg = nickName + "(" + uri + ") want to add you as buddy. Do you agree?";
		widget.ask(msg , new Listener<Boolean>() {
		    @Override
		    public void onEvent(Boolean yes) {
			if (yes.booleanValue()) {
			    subscriptionManager.approveSubscriptionRequest(uri, nickName);
			} else {
			    subscriptionManager.refuseSubscriptionRequest(uri);
			}
		    }
		});
	    }
	});

    }

    public void openChat(XmppURI uri) {
	chatManager.open(uri);
    }

    public void onItemClick(RosterItem item) {
	chatManager.open(item.getJID());
    }

    public void addBuddy(XmppURI uri) {
	roster.addItem(uri.getJID(), uri.getNode());
	widget.setAddBuddyPanelVisible(false);
	widget.setStatus("Add item request sent...");
    }

    public void toggleAddBuddyAction() {
	if (widget.isAddBuddyPanelVisible()) {
	    widget.setAddBuddyPanelVisible(false);
	    widget.setStatus("");
	} else {
	    widget.setAddBuddyPanelVisible(true);
	    widget.setStatus("Write a the jid you want to add");
	}
    }

}
