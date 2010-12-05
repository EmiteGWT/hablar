package com.calclab.hablar.group.client.manage;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.roster.RosterGroup;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * Displays a form to manage the modification of a group in roster. In fact, it
 * removes all the members from a group and put the new ones into a new group.
 */
public class ModifyGroupPresenter extends ManageGroupPresenter {

    private String oldGroupName;
    private final XmppRoster roster;

    public ModifyGroupPresenter(final XmppRoster roster, final HablarEventBus eventBus,
	    final ManageGroupDisplay display, final String pageTitle) {
	super(eventBus, display, pageTitle);
	this.roster = roster;
	display.getApply().addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(final ClickEvent event) {
		final String groupName = display.getGroupNameText();
		final Collection<RosterItem> items = display.getSelectedItems();
		final Map<XmppURI, RosterItem> uri2item = new HashMap<XmppURI, RosterItem>();
		final RosterGroup oldGroup = roster.getRosterGroup(oldGroupName);
		if (oldGroup != null) {
		    final Collection<RosterItem> oldItems = oldGroup.getItems();
		    for (final RosterItem item : oldItems) {
			uri2item.put(item.getJID(), item);
			item.removeFromGroup(oldGroupName);
		    }
		}
		for (final RosterItem item : items) {
		    RosterItem currentItem = uri2item.get(item.getJID());
		    if (currentItem == null) {
			currentItem = item;
			uri2item.put(currentItem.getJID(), currentItem);
		    }
		    currentItem.addToGroup(groupName);
		}
		roster.requestUpdateItems(uri2item.values());
		requestVisibility(Visibility.hidden);
	    }
	});
    }

    @Override
    protected void preloadForm() {
	display.clearSelectionList();
	display.getGroupName().setValue(oldGroupName);
	final RosterGroup group = roster.getRosterGroup(oldGroupName);
	final Set<XmppURI> addedUris = new HashSet<XmppURI>();
	if (group != null) {
	    for (final RosterItem item : group.getItems()) {
		display.addSelectedRosterItem(item);
		addedUris.add(item.getJID());
	    }
	}
	for (final RosterItem item : roster.getItems()) {
	    if (!addedUris.contains(item.getJID())) {
		display.addRosterItem(item);
	    }
	}
    }

    public void setOldGroupName(final String oldGroupName) {
	this.oldGroupName = oldGroupName;
    }
}
