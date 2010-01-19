package com.calclab.hablar.client.roster;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.client.ui.debug.Debug;
import com.calclab.hablar.client.ui.styles.HablarStyles;

public class RosterItemLogic {
    public static final String ROSTERITEM_MENU_DEB_ID = "RosterItemLogic-item-menu-";
    public static final String ROSTERITEM_NAME_DEB_ID = "RosterItemLogic-item-label-";

    public static HablarStyles.IconType getIcon(final RosterItem item) {
	final Show show = item.getShow();
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

    public static void setItem(final RosterItem item, final RosterItemView view) {
	view.setName(item.getName());
	view.setJID(item.getJID().toString());
	view.setNameDebugId(Debug.getIdFromJid(ROSTERITEM_NAME_DEB_ID, item.getJID()));
	view.setMenuDebugId(Debug.getIdFromJid(ROSTERITEM_MENU_DEB_ID, item.getJID()));
	view.setIcon(getIcon(item));

	final String status = item.getStatus();
	if (status != null && status.trim().length() > 0) {
	    view.setStatus(status);
	    view.setStatusVisible(true);
	} else {
	    view.setStatusVisible(false);
	}
	view.setItem(item);
    }

}
