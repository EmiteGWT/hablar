package com.calclab.hablar.core.client.ui.icon;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;

public class PresenceIcon {

    public static String get(final boolean available, final Show show) {
	if (show == Show.dnd) {
	    return Icons.BUDDY_DND;
	} else if (show == Show.xa) {
	    return Icons.BUDDY_WAIT;
	} else if (show == Show.away) {
	    return Icons.BUDDY_WAIT;
	} else if (show == Show.chat) {
	    return Icons.BUDDY_ON;
	} else if (available) {
	    return Icons.BUDDY_ON;
	} else {
	    return Icons.BUDDY_OFF;
	}
    }
}
