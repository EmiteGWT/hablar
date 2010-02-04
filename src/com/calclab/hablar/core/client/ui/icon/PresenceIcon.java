package com.calclab.hablar.core.client.ui.icon;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;

public class PresenceIcon {
    public static String getIcon(boolean available, Show show) {
	return HablarIcons.get(getIconType(available, show));
    }

    public static HablarIcons.IconType getIconType(boolean isAvailable, Show show) {
	if (show == Show.dnd) {
	    return HablarIcons.IconType.buddyDnd;
	} else if (show == Show.xa) {
	    return HablarIcons.IconType.buddyWait;
	} else if (show == Show.away) {
	    return HablarIcons.IconType.buddyWait;
	} else if (isAvailable) {
	    return HablarIcons.IconType.buddyOn;
	} else {
	    return HablarIcons.IconType.buddyOff;
	}
    }
}
