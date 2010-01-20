package com.calclab.hablar.basic.client.roster;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.basic.client.ui.icon.HablarIcons;
import com.calclab.hablar.basic.client.ui.utils.DebugId;

public class RosterItemLogic {
    public static final String ROSTERITEM_MENU_DEB_ID = "RosterItemLogic-item-menu-";
    public static final String ROSTERITEM_NAME_DEB_ID = "RosterItemLogic-item-label-";

    public static HablarIcons.IconType getIcon(final RosterItem item) {
	final Show show = item.getShow();
	if (show == Show.dnd) {
	    return HablarIcons.IconType.buddyDnd;
	} else if (show == Show.xa) {
	    return HablarIcons.IconType.buddyWait;
	} else if (show == Show.away) {
	    return HablarIcons.IconType.buddyWait;
	} else if (item.isAvailable()) {
	    return HablarIcons.IconType.buddyOn;
	} else {
	    return HablarIcons.IconType.buddyOff;
	}
    }

    public static void setItem(final RosterItem item, final RosterItemView view) {
	view.setName(item.getName());
	view.setJID(item.getJID().toString());
	view.setNameDebugId(DebugId.getFromJid(ROSTERITEM_NAME_DEB_ID, item.getJID()));
	view.setMenuDebugId(DebugId.getFromJid(ROSTERITEM_MENU_DEB_ID, item.getJID()));
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
