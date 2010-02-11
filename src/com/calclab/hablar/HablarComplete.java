package com.calclab.hablar;

import com.calclab.hablar.chat.client.HablarChat;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.dock.client.DockConfig;
import com.calclab.hablar.dock.client.HablarDock;
import com.calclab.hablar.dock.client.DockConfig.Position;
import com.calclab.hablar.editbuddy.client.HablarEditBuddy;
import com.calclab.hablar.groupchat.client.HablarGroupChat;
import com.calclab.hablar.logger.client.HablarLogger;
import com.calclab.hablar.login.client.HablarLogin;
import com.calclab.hablar.openchat.client.HablarOpenChat;
import com.calclab.hablar.openroom.client.HablarOpenRoom;
import com.calclab.hablar.rooms.client.HablarRooms;
import com.calclab.hablar.roster.client.HablarRoster;
import com.calclab.hablar.roster.client.RosterPage;
import com.calclab.hablar.search.client.HablarSearch;
import com.calclab.hablar.signals.client.HablarSignals;
import com.calclab.hablar.user.client.HablarUser;
import com.calclab.hablar.user.client.UserPage;
import com.calclab.hablar.vcard.client.HablarVCard;

public class HablarComplete {

    public static void install(final Hablar hablar, final HablarConfig config) {
	HablarChat.install(hablar);
	HablarRooms.install(hablar);
	HablarGroupChat.install(hablar);

	final DockConfig dock = new DockConfig();
	dock.set(Position.top, UserPage.TYPE, 24);
	if ("left".equals(config.dockRoster)) {
	    dock.set(Position.left, RosterPage.TYPE, 250);
	} else if ("right".equals(config.dockRoster)) {
	    dock.set(Position.right, RosterPage.TYPE, 250);
	}
	HablarDock.install(hablar, dock);

	HablarUser.install(hablar);
	HablarVCard.install(hablar);

	HablarLogger.install(hablar);

	if (config.hasLogin) {
	    HablarLogin.install(hablar);
	}

	if (config.hasRoster) {
	    HablarRoster.install(hablar);
	    HablarOpenChat.install(hablar);
	    HablarOpenRoom.install(hablar);
	    HablarEditBuddy.install(hablar);
	}

	if (config.hasSearch) {
	    HablarSearch.install(hablar);
	}

	if (config.hasSignals) {
	    HablarSignals.install(hablar);
	}
    }

    public static void install(final HablarWidget widget, final HablarConfig config) {
	final Hablar hablar = widget.getHablar();
	install(hablar, config);

    }

}
