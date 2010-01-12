package com.calclab.hablar.client.roster;

import java.util.Collection;
import java.util.HashMap;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;

public class RosterLogic {

    private final Roster roster;
    private final ChatManager chatManager;
    private final HashMap<XmppURI, RosterItemView> items;
    private final RosterView view;

    public RosterLogic(final RosterView view) {
	this.view = view;
	this.roster = Suco.get(Roster.class);
	this.items = new HashMap<XmppURI, RosterItemView>();
	this.chatManager = Suco.get(ChatManager.class);

	roster.onRosterRetrieved(new Listener<Collection<RosterItem>>() {
	    @Override
	    public void onEvent(Collection<RosterItem> items) {
		GWT.log("Retrieved roster", null);
		for (RosterItem item : items) {
		    getWidget(item);
		}
	    }

	});

	roster.onItemChanged(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(RosterItem item) {
		getWidget(item).setItem(item);
	    }
	});

	// final SubscriptionManager subscriptionManager =
	// Suco.get(SubscriptionManager.class);
	// subscriptionManager.onSubscriptionRequested(new Listener2<XmppURI,
	// String>() {
	// @Override
	// public void onEvent(final XmppURI uri, final String nickName) {
	// String msg = nickName + "(" + uri +
	// ") want to add you as buddy. Do you agree?";
	// widget.ask(msg, new Listener<Boolean>() {
	// @Override
	// public void onEvent(Boolean yes) {
	// if (yes.booleanValue()) {
	// subscriptionManager.approveSubscriptionRequest(uri, nickName);
	// } else {
	// subscriptionManager.refuseSubscriptionRequest(uri);
	// }
	// }
	// });
	// }
	// });

    }

    public void onItemClick(RosterItem item) {
	chatManager.open(item.getJID());
    }

    public void openChat(XmppURI uri) {
	chatManager.open(uri);
    }

    private RosterItemView getWidget(RosterItem item) {
	RosterItemView itemWidget = items.get(item.getJID());
	if (itemWidget == null) {
	    itemWidget = view.addItem(item);
	    items.put(item.getJID(), itemWidget);
	}
	return itemWidget;
    }

}
