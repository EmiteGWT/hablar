package com.calclab.hablar.icons.client;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.google.gwt.resources.client.ImageResource;

public class PresenceIcon {

	public static ImageResource get(final boolean available, final Show show) {
		if (show == Show.dnd) {
			return IconsBundle.bundle.buddyIconDnd();
		} else if (show == Show.xa) {
			return IconsBundle.bundle.buddyIconWait();
		} else if (show == Show.away) {
			return IconsBundle.bundle.buddyIconWait();
		} else if (show == Show.chat) {
			return IconsBundle.bundle.buddyIconOn();
		} else if (available) {
			return IconsBundle.bundle.buddyIconOn();
		} else {
			return IconsBundle.bundle.buddyIconOff();
		}
	}
}
