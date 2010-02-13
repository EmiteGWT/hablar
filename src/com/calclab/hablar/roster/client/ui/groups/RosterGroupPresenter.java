package com.calclab.hablar.roster.client.ui.groups;

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
	this.groupName = groupName;
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
		if (items.containsKey(item.getJID())) {
		    getPresenter(item).setItem(item);
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

}
