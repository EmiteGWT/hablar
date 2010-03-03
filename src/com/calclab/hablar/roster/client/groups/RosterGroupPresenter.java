package com.calclab.hablar.roster.client.groups;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.roster.RosterGroup;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.Idify;
import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.suco.client.events.Listener;

public class RosterGroupPresenter implements Presenter<RosterGroupDisplay> {
    private final RosterGroupDisplay display;
    private String groupLabel;
    private final HashMap<XmppURI, RosterItemPresenter> itemPresenters;
    private final Menu<RosterItemPresenter> itemMenu;

    private final RosterGroup group;

    public RosterGroupPresenter(final RosterGroup group, final Menu<RosterItemPresenter> itemMenu,
	    final RosterGroupDisplay display) {
	this.group = group;
	this.itemMenu = itemMenu;
	this.display = display;

	itemPresenters = new HashMap<XmppURI, RosterItemPresenter>();
	display.setVisible(group.isAllContacts());

	final Collection<RosterItem> rosterItems = group.getItems();
	for (final RosterItem item : rosterItems) {
	    getPresenter(item);
	}
	// reorder();

	group.onItemAdded(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(final RosterItem item) {
		getPresenter(item);
		// reorder();
	    }
	});

	group.onItemChanged(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(final RosterItem item) {
		getPresenter(item).setItem(item);
		// reorder();
	    }
	});

	group.onItemRemoved(new Listener<RosterItem>() {
	    @Override
	    public void onEvent(final RosterItem item) {
		remove(item.getJID());
		// reorder();
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
	return group.getName();
    }

    public RosterGroup getRosterGroup() {
	return group;
    }

    public void toggleVisibility() {
	display.setVisible(!display.isVisible());
    }

    private RosterItemPresenter createRosterItem(final RosterItem item) {
	// FIXME: no mola nada toda esta basura selenium
	final RosterItemDisplay itemDisplay = display.newRosterItemDisplay(Idify.id(group.getName()), Idify.id(item
		.getJID()));
	final RosterItemPresenter presenter = new RosterItemPresenter(group.getName(), itemMenu, itemDisplay);
	itemPresenters.put(item.getJID(), presenter);
	return presenter;
    }

    private RosterItemPresenter getPresenter(final RosterItem item) {
	RosterItemPresenter presenter = itemPresenters.get(item.getJID());
	if (presenter == null) {
	    presenter = createRosterItem(item);
	    display.add(presenter.getDisplay());
	}
	presenter.setItem(item);
	return presenter;
    }

    private void reorder() {
	final ArrayList<RosterItem> list = new ArrayList<RosterItem>(group.getItems());
	Collections.sort(list, new Comparator<RosterItem>() {
	    @Override
	    public int compare(final RosterItem item1, final RosterItem item2) {
		return item1.getJID().toString().compareTo(item2.getJID().toString());
	    }
	});
	display.removeAll();
	for (final RosterItem item : list) {
	    final RosterItemPresenter presenter = itemPresenters.get(item.getJID());
	    display.add(presenter.getDisplay());
	}
    }

    protected void remove(final XmppURI itemJid) {
	final RosterItemPresenter presenter = itemPresenters.remove(itemJid);
	display.remove(presenter.getDisplay());
    }

}
