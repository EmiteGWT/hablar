package com.calclab.hablar;

import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.emite.xep.muc.client.RoomManager;
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

    public static void install(final Hablar hablar, final HablarConfig config, final EmiteCompleteGinjector ginjector) {
	final XmppSession session = ginjector.getXmppSession();
	final XmppRoster roster = ginjector.getXmppRoster();
	final ChatManager chatManager = ginjector.getChatManager();
	final RoomManager roomManager = ginjector.getRoomManager();

	new HablarCore(hablar);
	new HablarChat(hablar, config.chatConfig, roster, chatManager, ginjector.getStateManager());
	new HablarRooms(hablar, config.roomsConfig, session, roster, roomManager, ginjector.getRoomDiscoveryManager(),
		ginjector.getMUCChatStateManager());
	new HablarGroupChat(hablar, config.roomsConfig, session, roster, chatManager, roomManager);

	new HablarDock(hablar, config.dockConfig);

	new HablarUser(hablar, session, ginjector.getPresenceManager(), ginjector.getPrivateStorageManager());

	RosterPage rosterPage = null;
	HablarRoster hablarRoster = null;
	if (config.hasRoster) {
	    hablarRoster = new HablarRoster(hablar, config.rosterConfig, session, roster, chatManager,
		    ginjector.getSubscriptionHandler());
	    rosterPage = hablarRoster.getRosterPage();
	}

	if (config.hasVCard) {
	    new HablarVCard(hablar, config.vcardConfig, session, roster, ginjector.getVCardManager());
	}

	if (config.hasRoster) {
	    new HablarOpenChat(hablar, session, roster, chatManager);
	    new HablarEditBuddy(hablar, roster);
	    new HablarUserGroups(rosterPage, hablar, roster);
	    new HablarGroup(hablar, session, roster);
	    hablarRoster.addLowPriorityActions();
	}

	if (config.hasSearch) {
	    new HablarSearch(hablar, config.searchConfig, session, roster, chatManager, ginjector.getSearchManager());
	}

	if (config.hasSignals) {
	    new HablarSignals(hablar, session, ginjector.getPrivateStorageManager());
	}

	if (config.hasSound) {
	    new HablarSoundSignals(hablar, config.soundConfig);
	}

	if (config.hasCopyToClipboard) {
	    new HablarClipboard(hablar);
	}

    }

    public static void install(final HablarWidget widget, final HablarConfig config,
	    final EmiteCompleteGinjector ginjector) {
	final Hablar hablar = widget.getHablar();
	install(hablar, config, ginjector);

    }

}
