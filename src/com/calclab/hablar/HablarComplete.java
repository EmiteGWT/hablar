package com.calclab.hablar;

import com.calclab.hablar.chat.client.HablarChat;
import com.calclab.hablar.clipboard.client.HablarClipboard;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarCore;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.dock.client.HablarDock;
import com.calclab.hablar.editbuddy.client.HablarEditBuddy;
import com.calclab.hablar.group.client.HablarGroup;
import com.calclab.hablar.groupchat.client.HablarGroupChat;
import com.calclab.hablar.openchat.client.HablarOpenChat;
import com.calclab.hablar.rooms.client.HablarRooms;
import com.calclab.hablar.roster.client.HablarRoster;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.hablar.search.client.HablarSearch;
import com.calclab.hablar.signals.client.HablarSignals;
import com.calclab.hablar.signals.sound.client.HablarSoundSignals;
import com.calclab.hablar.user.client.HablarUser;
import com.calclab.hablar.usergroups.client.HablarUserGroups;
import com.calclab.hablar.vcard.client.HablarVCard;

public class HablarComplete {

    public static void install(final Hablar hablar, final HablarConfig config) {
	new HablarCore(hablar);
	new HablarChat(hablar, config.chatConfig);
	new HablarRooms(hablar, config.roomsConfig);
	new HablarGroupChat(hablar, config.roomsConfig);

	new HablarDock(hablar, config.dockConfig);

	new HablarUser(hablar);

	RosterPage roster = null;
	HablarRoster hablarRoster = null;
	if (config.hasRoster) {
	    hablarRoster = new HablarRoster(hablar, config.rosterConfig);
	    roster = hablarRoster.getRosterPage();
	}

	if (config.hasVCard) {
	    new HablarVCard(hablar, config.vcardConfig);
	}

	if (config.hasRoster) {
	    new HablarOpenChat(hablar);
	    new HablarEditBuddy(hablar);
	    new HablarUserGroups(roster, hablar);
	    new HablarGroup(hablar);
	    hablarRoster.addLowPriorityActions();
	}

	if (config.hasSearch) {
	    new HablarSearch(hablar, config.searchConfig);
	}

	if (config.hasSignals) {
	    new HablarSignals(hablar);
	}

	if (config.hasSound) {
	    new HablarSoundSignals(hablar, config.soundConfig);
	}

	if (config.hasCopyToClipboard) {
	    new HablarClipboard(hablar);
	}

    }

    public static void install(final HablarWidget widget, final HablarConfig config) {
	final Hablar hablar = widget.getHablar();
	install(hablar, config);

    }

}
