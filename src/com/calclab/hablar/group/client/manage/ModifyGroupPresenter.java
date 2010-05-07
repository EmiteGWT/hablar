package com.calclab.hablar.group.client.manage;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterGroup;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.suco.client.Suco;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

/**
 * Displays a form to manage the modification of a group in roster. In fact, it
 * removes all the members from a group and put the new ones into a new group.
 */
public class ModifyGroupPresenter extends ManageGroupPresenter {

    private String oldGroupName;

    public ModifyGroupPresenter(HablarEventBus eventBus, final ManageGroupDisplay display, String pageTitle) {
	super(eventBus, display, pageTitle);
	display.getApply().addClickHandler(new ClickHandler() {

	    @Override
	    public void onClick(ClickEvent event) {
		String groupName = display.getGroupNameText();
		Collection<RosterItem> items = display.getSelectedItems();
		Roster roster = Suco.get(Roster.class);
		Map<XmppURI, RosterItem> uri2item = new HashMap<XmppURI, RosterItem>();
		RosterGroup oldGroup = roster.getRosterGroup(oldGroupName);
		if (oldGroup != null) {
		    Collection<RosterItem> oldItems = oldGroup.getItems();
		    for (RosterItem item : oldItems) {
			uri2item.put(item.getJID(), item);
			item.removeFromGroup(oldGroupName);
		    }
		}
		for (RosterItem item : items) {
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

    public void setOldGroupName(String oldGroupName) {
	this.oldGroupName = oldGroupName;
    }

    protected void preloadForm() {
	display.clearSelectionList();
	display.getGroupName().setValue(oldGroupName);
	Roster roster = Suco.get(Roster.class);
	RosterGroup group = roster.getRosterGroup(oldGroupName);
	Set<XmppURI> addedUris = new HashSet<XmppURI>();
	if (group != null) {
	    for (RosterItem item : group.getItems()) {
		display.addSelectedRosterItem(item);
		addedUris.add(item.getJID());
	    }
	}
	for (RosterItem item: roster.getItems()) {
	    if (!addedUris.contains(item.getJID())) {
		display.addRosterItem(item);
	    }
	}
    }
}
