package com.calclab.hablar.roster.client.groups;

import java.util.Collection;
import java.util.HashMap;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;

public class RosterGroupPresenter implements Presenter<RosterGroupDisplay> {
    private final RosterGroupDisplay display;
    private final String groupLabel;
    private final HashMap<XmppURI, RosterItemPresenter> items;
    private final Menu<RosterItemPresenter> itemMenu;
    private final String groupName;

    public RosterGroupPresenter(final String groupName, final Menu<RosterItemPresenter> itemMenu,
	    final RosterGroupDisplay display) {
	this.groupName = groupName == null ? "" : groupName;
	this.itemMenu = itemMenu;
	this.display = display;

	items = new HashMap<XmppURI, RosterItemPresenter>();
	final boolean isAllContacts = isAllContacts();
	display.setVisible(isAllContacts);

	final Roster roster = Suco.get(Roster.class);
	final Collection<RosterItem> rosterItems = isAllContacts ? roster.getItems() : roster
		.getItemsByGroup(groupName);
	for (final RosterItem item : rosterItems) {
	    getPresenter(item);
	}
	groupLabel = isAllContacts ? "All contacts" : "Group: " + groupName + " (" + items.size() + ")";

	roster.onItemChanged(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(final RosterItem item) {
		final XmppURI itemJid = item.getJID();
		if (items.containsKey(itemJid)) {
		    final RosterItemPresenter presenter = getPresenter(item);
		    if (isAllContacts || item.isInGroup(groupName)) {
			presenter.setItem(item);
		    } else {
			remove(itemJid, presenter);
		    }
		} else if (item.isInGroup(groupName)) {
		    getPresenter(item);
		}
	    }
	});

	roster.onItemAdded(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(final RosterItem item) {
		if (isInGroup(item)) {
		    getPresenter(item);
		}
	    }
	});

	roster.onItemRemoved(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(final RosterItem item) {
		final XmppURI jid = item.getJID();
		if (items.containsKey(jid)) {
		    remove(jid, items.get(jid));
		}
	    }
	});
    }

    @Override
    public RosterGroupDisplay getDisplay() {
	return display;
    }

    public String getGroupLabel() {
	return groupLabel;
    }

    public String getGroupName() {
	return groupName;
    }

    public boolean isAllContacts() {
	return groupName.length() == 0;
    }

    public void toggleVisibility() {
	display.setVisible(!display.isVisible());
    }

    private RosterItemPresenter createRosterItem(final RosterItem item) {
	final RosterItemDisplay itemDisplay = display.newRosterItemDisplay();
	final RosterItemPresenter presenter = new RosterItemPresenter(groupName, itemMenu, itemDisplay);
	display.add(itemDisplay);
	items.put(item.getJID(), presenter);
	return presenter;
    }

    private RosterItemPresenter getPresenter(final RosterItem item) {
	RosterItemPresenter presenter = items.get(item.getJID());
	if (presenter == null) {
	    presenter = createRosterItem(item);
	}
	presenter.setItem(item);
	return presenter;
    }

    private boolean isInGroup(final RosterItem item) {
	if (isAllContacts()) {
	    return true;
	} else {
	    for (final String name : item.getGroups()) {
		if (name.equals(groupName)) {
		    return true;
		}
	    }
	}
	return false;
    }

    protected void remove(final XmppURI itemJid, final RosterItemPresenter presenter) {
	display.remove(presenter.getDisplay());
	items.remove(itemJid);
    }

}
