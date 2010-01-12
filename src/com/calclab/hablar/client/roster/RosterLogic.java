package com.calclab.hablar.client.roster;

import java.util.Collection;
import java.util.HashMap;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.im.client.roster.SubscriptionManager;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.calclab.suco.client.events.Listener2;
import com.google.gwt.core.client.GWT;

public class RosterLogic {

    private final Roster roster;
    private final ChatManager chatManager;
    private final RosterPage widget;
    private final HashMap<XmppURI, RosterItemWidget> items;

    public RosterLogic(final RosterPage widget) {
	this.widget = widget;
	this.roster = Suco.get(Roster.class);
	this.items = new HashMap<XmppURI, RosterItemWidget>();
	this.chatManager = Suco.get(ChatManager.class);

	roster.onRosterRetrieved(new Listener<Collection<RosterItem>>() {
	    @Override
	    public void onEvent(Collection<RosterItem> items) {
		GWT.log("Retrieved roster", null);
		for (RosterItem item : items) {
		    GWT.log("Item!" + item.getName(), null);
		    widget.addItem(getWidget(item));
		}
	    }

	});

	roster.onItemChanged(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(RosterItem item) {
		getWidget(item).setItem(item);
	    }
	});

	final SubscriptionManager subscriptionManager = Suco.get(SubscriptionManager.class);
	subscriptionManager.onSubscriptionRequested(new Listener2<XmppURI, String>() {
	    @Override
	    public void onEvent(final XmppURI uri, final String nickName) {
		String msg = nickName + "(" + uri + ") want to add you as buddy. Do you agree?";
		widget.ask(msg, new Listener<Boolean>() {
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

    public void addBuddy(XmppURI uri) {
	roster.addItem(uri.getJID(), uri.getNode());
	widget.setAddBuddyPanelVisible(false);
	widget.setStatus("Add item request sent...");
    }

    public void onItemClick(RosterItem item) {
	chatManager.open(item.getJID());
    }

    public void openChat(XmppURI uri) {
	chatManager.open(uri);
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

    private RosterItemWidget getWidget(RosterItem item) {
	RosterItemWidget itemWidget = items.get(item.getJID());
	if (itemWidget == null) {
	    itemWidget = new RosterItemWidget(item, RosterLogic.this);
	    items.put(item.getJID(), itemWidget);
	}
	return itemWidget;
    }

}
