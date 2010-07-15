package com.calclab.hablar.core.client.ui.icon;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.google.gwt.resources.client.ImageResource;

public class PresenceIcon {

    public static ImageResource getIconResource(final boolean available, final Show show) {
	HablarIconsBundle bundle = OldHablarIcons.getBundle();
	if (show == Show.dnd) {
	    return bundle.buddyIconDnd();
	} else if (show == Show.xa) {
	    return bundle.buddyIconWait();
	} else if (show == Show.away) {
	    return bundle.buddyIconWait();
	} else if (show == Show.chat) {
	    return bundle.buddyIconOn();
	} else if (available) {
	    return bundle.buddyIconOn();
	} else {
	    return bundle.buddyIconOff();
	}
    }
}
