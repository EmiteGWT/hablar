package com.calclab.hablar.client.roster;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.client.ui.icons.Icons.IconType;

public class RosterItemLogic {
    public static IconType getIcon(RosterItem item) {
	Show show = item.getShow();
	if (show == Show.dnd) {
	    return IconType.buddyDnd;
	} else if (show == Show.xa) {
	    return IconType.buddyWait;
	} else if (show == Show.away) {
	    return IconType.buddyWait;
	} else if (item.isAvailable()) {
	    return IconType.buddyOn;
	} else {
	    return IconType.buddyOn;
	}
    }

    public static void setItem(RosterItem item, RosterItemView view) {
	view.setName(item.getName());
	view.setJID(item.getJID().toString());
	view.setIcon(getIcon(item));

	String status = item.getStatus();
	if (status != null && status.trim().length() > 0) {
	    view.setStatus(status);
	    view.setStatusVisible(true);
	} else {
	    view.setStatusVisible(false);
	}
	view.setItem(item);
    }

}
