package com.calclab.hablar.client.roster;

import java.util.Collection;
import java.util.HashMap;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.client.ui.lists.ListItemView;
import com.calclab.hablar.client.ui.lists.ListLogic;
import com.calclab.hablar.client.ui.menu.MenuAction;
import com.calclab.hablar.client.ui.menu.PopupMenuView;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.UIObject;

public class RosterLogic implements ListLogic {

    private final Roster roster;
    private final ChatManager chatManager;
    private final HashMap<XmppURI, RosterItemView> items;
    private final RosterView view;
    private boolean active;
    private PopupMenuView<RosterItem> menu;

    public RosterLogic(final RosterView view) {
	this.view = view;
	this.roster = Suco.get(Roster.class);
	this.items = new HashMap<XmppURI, RosterItemView>();
	this.chatManager = Suco.get(ChatManager.class);
	createMenu();
	roster.onRosterRetrieved(new Listener<Collection<RosterItem>>() {
	    @Override
	    public void onEvent(Collection<RosterItem> items) {
		GWT.log("Retrieved roster", null);
		for (RosterItem item : items) {
		    getOrCreateWidget(item);
		}
	    }

	});

	roster.onItemAdded(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(RosterItem item) {
		getOrCreateWidget(item);
		view.setStatusMessage(item.getName() + " has been added to roster.");
	    }
	});

	roster.onItemChanged(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(RosterItem item) {
		getOrCreateWidget(item);
	    }
	});

	Session session = Suco.get(Session.class);
	session.onStateChanged(new Listener<Session>() {
	    @Override
	    public void onEvent(Session session) {
		boolean isActive = session.getState() == State.ready;
		if (active != isActive) {
		    active = isActive;
		    view.setActive(isActive);
		}
	    }
	});

	this.active = session.getState() == State.ready;
	view.setActive(active);
    }

    public void onItemClick(ListItemView view, Event event) {
	RosterItemView rosterView = (RosterItemView) view;
	chatManager.open(rosterView.getItem().getJID());
    }

    @Override
    public void onMenuClicked(ListItemView view, UIObject ui) {
	RosterItem item = ((RosterItemView) view).getItem();
	if (menu.isVisible()) {
	    GWT.log("menu visible!", null);
	    menu.hide();
	} else {
	    menu.setTarget(item);
	    menu.showRelativeToMenu(ui);
	}
    }

    @Override
    public void onMouseOver(ListItemView view, boolean over) {
	view.setSelected(over);
	view.setMenuVisible(over);
    }

    public void openChat(XmppURI uri) {
	chatManager.open(uri);
    }

    @SuppressWarnings("unchecked")
    private void createMenu() {
	menu = view.createMenu(new MenuAction<RosterItem>("Chat") {
	    @Override
	    public void execute(RosterItem target) {
		onChatWith(target);
	    }
	}, new MenuAction<RosterItem>("Remove from roster") {
	    @Override
	    public void execute(RosterItem target) {
		onRemoveItem(target);
	    }
	});
    }

    /**
     * Returns a roster item view ALWAYS
     * 
     * @param item
     * @return
     */
    RosterItemView getOrCreateWidget(RosterItem item) {
	RosterItemView itemWidget = items.get(item.getJID());
	if (itemWidget == null) {
	    itemWidget = view.createItemView();
	    items.put(item.getJID(), itemWidget);
	}
	RosterItemLogic.setItem(item, itemWidget);
	return itemWidget;
    }

    void onChatWith(RosterItem item) {
	chatManager.open(item.getJID());
    }

    void onRemoveItem(RosterItem item) {
	roster.removeItem(item.getJID());
    }

}
