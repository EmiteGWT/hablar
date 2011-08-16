package com.calclab.hablar.html.client;

import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.emite.xep.muc.client.RoomManager;
import com.calclab.hablar.chat.client.HablarChat;
import com.calclab.hablar.client.HablarConfig;
import com.calclab.hablar.client.HablarGinjector;
import com.calclab.hablar.clipboard.client.HablarClipboard;
import com.calclab.hablar.console.client.HablarConsole;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarCore;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.browser.BrowserFocusHandler;
import com.calclab.hablar.dock.client.HablarDock;
import com.calclab.hablar.editbuddy.client.HablarEditBuddy;
import com.calclab.hablar.group.client.HablarGroup;
import com.calclab.hablar.groupchat.client.HablarGroupChat;
import com.calclab.hablar.login.client.HablarLogin;
import com.calclab.hablar.login.client.LoginConfig;
import com.calclab.hablar.openchat.client.HablarOpenChat;
import com.calclab.hablar.rooms.client.HablarRooms;
import com.calclab.hablar.roster.client.HablarRoster;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.calclab.hablar.search.client.HablarSearch;
import com.calclab.hablar.signals.client.HablarSignals;
import com.calclab.hablar.signals.client.sound.HablarSoundSignals;
import com.calclab.hablar.user.client.HablarUser;
import com.calclab.hablar.usergroups.client.HablarUserGroups;
import com.calclab.hablar.vcard.client.HablarVCard;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Module to allow easily embedding hablar inside a webpage, without having to
 * embed it within another GWT application
 */
public class HablarHtml implements EntryPoint {
	private void addHablarToDiv(final HablarWidget hablar, final HtmlConfig htmlConfig) {
		setSize(hablar, htmlConfig);
		final RootPanel rootPanel = RootPanel.get(htmlConfig.inline);
		if (rootPanel != null) {
			rootPanel.add(hablar);
		} else {
			throw new RuntimeException("The div with id " + htmlConfig.inline + " is not found.");
		}
	}

	private DialogBox createDialog(final HablarWidget widget, final HtmlConfig htmlConfig) {
		final DialogBox dialog = new DialogBox();
		dialog.setText("Hablar");
		setSize(dialog, htmlConfig);
		dialog.show();
		dialog.center();
		return dialog;
	}

	@Override
	public void onModuleLoad() {
		final HablarGinjector ginjector = GWT.create(HablarGinjector.class);

		// We will instantiate the BrowserFocusHandler singleton so that it
		// starts tracking focus events as soon as possible.
		BrowserFocusHandler.getInstance();
		final HablarConfig config = HablarConfig.getFromMeta();
		final HtmlConfig htmlConfig = HtmlConfig.getFromMeta();
		htmlConfig.hasLogger = true;
		final HablarWidget widget = new HablarWidget(config.layout, config.tabHeaderSize);
		final Hablar hablar = widget.getHablar();

		final XmppSession session = ginjector.getXmppSession();
		final XmppRoster roster = ginjector.getXmppRoster();
		final ChatManager chatManager = ginjector.getChatManager();
		final RoomManager roomManager = ginjector.getRoomManager();

		new HablarCore(hablar);
		new HablarChat(hablar, config.chatConfig, roster, chatManager, ginjector.getStateManager());
		new HablarRooms(hablar, config.roomsConfig, session, roster, roomManager, ginjector.getRoomDiscoveryManager(), ginjector.getMUCChatStateManager());
		new HablarGroupChat(hablar, config.roomsConfig, session, roster, chatManager, roomManager);
		new HablarDock(hablar, config.dockConfig);
		new HablarUser(hablar, session, ginjector.getPresenceManager(), ginjector.getPrivateStorageManager());

		RosterPage rosterPage = null;
		HablarRoster hablarRoster = null;
		if (config.hasRoster) {
			hablarRoster = new HablarRoster(hablar, config.rosterConfig, session, roster, chatManager, ginjector.getSubscriptionHandler());
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
			new HablarSoundSignals(hablar);
		}

		if (config.hasCopyToClipboard) {
			new HablarClipboard(hablar);
		}

		if (htmlConfig.hasLogger) {
			new HablarConsole(hablar, ginjector.getXmppConnection(), session);
		}

		if (htmlConfig.hasLogin) {
			new HablarLogin(hablar, LoginConfig.getFromMeta(), session);
		}

		if (htmlConfig.inline == null) {
			createDialog(widget, htmlConfig);
		} else {
			addHablarToDiv(widget, htmlConfig);
		}
	}

	private void setSize(final Widget widget, final HtmlConfig htmlConfig) {
		if (htmlConfig.width != null) {
			widget.setWidth(htmlConfig.width);
		}
		if (htmlConfig.height != null) {
			widget.setHeight(htmlConfig.height);
		}
	}

}
