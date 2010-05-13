package com.calclab.hablar.roster.client.selection;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.ui.selectionlist.Selectable;
import com.calclab.hablar.roster.client.groups.RosterItemWidget;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Widget;

public class RosterItemSelectable implements Selectable {

    private RosterItem rosterItem;

    private RosterItemWidget widget;

    public RosterItemSelectable(RosterItem rosterItem, RosterItemWidget widget) {
	this.rosterItem = rosterItem;
	this.widget = widget;
    }

    @Override
    public HasClickHandlers getAction() {
	return widget.getAction();
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
	return widget;
    }

    @Override
    public int compareTo(Selectable o) {
	RosterItemSelectable riSelectable = (RosterItemSelectable) o;
	RosterItem otherRosterItem = riSelectable.rosterItem;
	int retValue = statusValue(rosterItem) - statusValue(otherRosterItem);
	if (retValue == 0) {
	    retValue = rosterItem.getName().compareTo(otherRosterItem.getName());
	}
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
