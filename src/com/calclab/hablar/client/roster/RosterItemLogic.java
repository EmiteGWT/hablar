package com.calclab.hablar.client.roster;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.roster.RosterItem;

public class RosterItemLogic {
    public static String getIcon(RosterItem item, RosterItemIcons icons) {
	Show show = item.getShow();
	if (show == Show.dnd) {
	    return icons.buddyIconDnd();
	} else if (show == Show.xa) {
	    return icons.buddyIconWait();
	} else if (show == Show.away) {
	    return icons.buddyIconOff();
	} else if (item.isAvailable()) {
	    return icons.buddyIconOn();
	} else {
	    return icons.buddyIconOff();
	}
    }

    public static void setItem(RosterItem item, RosterItemView view) {
	view.setName(item.getName());
	view.setJID(item.getJID().toString());
	view.setIcon(getIcon(item, view.getIconStyles()));

	String status = item.getStatus();
	if (status != null && status.trim().length() > 0) {
	    view.setStatus(status);
	    view.setStatusVisible(true);
	} else {
	    view.setStatusVisible(false);
	}

    }

}
