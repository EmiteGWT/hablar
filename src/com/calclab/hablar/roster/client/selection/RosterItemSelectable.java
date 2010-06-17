package com.calclab.hablar.roster.client.selection;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.ui.selectionlist.Selectable;
import com.calclab.hablar.roster.client.groups.RosterItemDisplay;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Widget;

public class RosterItemSelectable implements Selectable {

    private RosterItem rosterItem;

    private RosterItemDisplay rosterItemDisplay;

    public RosterItemSelectable(RosterItem rosterItem, RosterItemDisplay rosterItemDisplay) {
	this.rosterItem = rosterItem;
	this.rosterItemDisplay = rosterItemDisplay;
    }

    @Override
    public HasClickHandlers getAction() {
	return rosterItemDisplay.getAction();
    }

    @Override
    public String getId() {
	return rosterItem.getJID().toString();
    }

    @Override
    public RosterItem getItem() {
	return rosterItem;
    }

    @Override
    public Widget getWidget() {
	return rosterItemDisplay.asWidget();
    }

    @Override
    public int compareTo(Selectable o) {
	// Short cut if the objects are equal
	if (o.equals(this)) {
	    return 0;
	}

	RosterItemSelectable riSelectable = (RosterItemSelectable) o;
	RosterItem otherRosterItem = riSelectable.rosterItem;
	// First compare by the status
	int retValue = statusValue(rosterItem) - statusValue(otherRosterItem);

	// Then compare by the name (if possible)
	if (retValue == 0) {
	    // We want people with a name to sort higher than people without a
	    // name
	    if ((rosterItem.getName() == null) && (otherRosterItem.getName() != null)) {
		retValue = 1;
	    } else if ((rosterItem.getName() != null) && (otherRosterItem.getName() == null)) {
		retValue = -1;
	    } else if ((rosterItem.getName() != null) && (otherRosterItem.getName() != null)) {
		retValue = rosterItem.getName().compareTo(otherRosterItem.getName());
	    }
	}

	// Then compare by the jid
	if (retValue == 0) {
	    if ((rosterItem.getJID() != null) && (otherRosterItem.getJID() != null)) {
		retValue = rosterItem.getJID().toString().compareTo(otherRosterItem.getJID().toString());
	    }
	}

	// If it's still zero here we need to do something about it as we should
	// only return 0 if x.equals(y). It can't be true here as we would have
	// bypassed it at the beginning or the method.
	if (retValue == 0) {
	    retValue = this.hashCode() - riSelectable.hashCode();
	}

	assert retValue != 0;

	return retValue;
    }

    private int statusValue(RosterItem item) {
	Show show = item.getShow();
	if (show == Show.dnd) {
	    return 2;
	} else if (show == Show.xa) {
	    return 4;
	} else if (show == Show.away) {
	    return 3;
	} else if (show == Show.chat) {
	    return 0;
	} else if (item.isAvailable()) {
	    return 0;
	} else {
	    return 5;
	}
    }
}
