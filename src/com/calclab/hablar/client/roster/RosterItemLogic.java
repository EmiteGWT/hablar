package com.calclab.hablar.client.roster;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.client.ui.styles.HablarStyles;
import com.calclab.hablar.client.ui.styles.HablarStyles.IconType;

public class RosterItemLogic {
    public static HablarStyles.IconType getIcon(RosterItem item) {
	Show show = item.getShow();
	if (show == Show.dnd) {
	    return HablarStyles.IconType.buddyDnd;
	} else if (show == Show.xa) {
	    return HablarStyles.IconType.buddyWait;
	} else if (show == Show.away) {
	    return HablarStyles.IconType.buddyWait;
	} else if (item.isAvailable()) {
	    return HablarStyles.IconType.buddyOn;
	} else {
	    return HablarStyles.IconType.buddyOff;
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
