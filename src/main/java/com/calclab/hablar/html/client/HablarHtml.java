package com.calclab.hablar.html.client;

import java.util.HashMap;
import java.util.logging.Logger;

import com.calclab.emite.core.client.LoginXmpp;
import com.calclab.emite.core.client.events.GwtEmiteEventBus;
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
	private static final Logger logger = Logger.getLogger(HablarHtml.class.getName());
	
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
		String loginId=htmlConfig.loginId;
		
		
		if (loginId == null) {
			logger.info("Trying to use the browser module outside of GWT apps without setting <meta id=\"hablar.loginId\" content=\"exampleuserid\" /> is not supported");
			logger.info("<meta id=\"hablar.loginId\" content=\"exampleuserid\" /> should match <meta id=\"emite.user\" content=\"exampleuserid\" /> otherwise this instance of hablar will not know which instance of Emite to use.");		
		}
		
		if (loginId != null) {
		
		//final HablarGinjector ginjector = GWT.create(HablarGinjector.class);		
		HashMap <String, LoginXmpp>loginXmppMap = ginjector.getLoginXmppMap();    	      		
		LoginXmpp loginXmpp = loginXmppMap.get(loginId);
		
		new HablarCore(hablar);
		new HablarChat(hablar, config.chatConfig, loginXmpp.xmppRoster, loginXmpp.chatManager, loginXmpp.stateManager);
		new HablarRooms(hablar, config.roomsConfig, loginXmpp.xmppSession, loginXmpp.xmppRoster, loginXmpp.roomManager, loginXmpp.roomDiscoveryManager, loginXmpp.mucChatStateManager);
		new HablarGroupChat(hablar, config.roomsConfig, loginXmpp.xmppSession, loginXmpp.xmppRoster, loginXmpp.chatManager, loginXmpp.roomManager);
		new HablarDock(hablar, config.dockConfig);
		new HablarUser(hablar, loginXmpp.xmppSession, loginXmpp.presenceManager, loginXmpp.privateStorageManager);

		RosterPage rosterPage = null;
		HablarRoster hablarRoster = null;
		if (config.hasRoster) {
			hablarRoster = new HablarRoster(hablar, config.rosterConfig, loginXmpp.xmppSession, loginXmpp.xmppRoster, loginXmpp.chatManager, loginXmpp.subscriptionHandler);
			rosterPage = hablarRoster.getRosterPage();
		}

		if (config.hasVCard) {
			new HablarVCard(hablar, config.vcardConfig, loginXmpp.xmppSession, loginXmpp.xmppRoster, ginjector.getVCardManager());
		}

		if (config.hasRoster) {
			new HablarOpenChat(hablar, loginXmpp.xmppSession, loginXmpp.xmppRoster, loginXmpp.chatManager);
			new HablarEditBuddy(hablar, loginXmpp.xmppRoster); 
			new HablarUserGroups(rosterPage, hablar, loginXmpp.xmppRoster);
			new HablarGroup(hablar, loginXmpp.xmppSession, loginXmpp.xmppRoster);
			hablarRoster.addLowPriorityActions();
		}

		if (config.hasSearch) {
			new HablarSearch(hablar, config.searchConfig, loginXmpp.xmppSession, loginXmpp.xmppRoster, loginXmpp.chatManager, loginXmpp.searchManager);
		}

		if (config.hasSignals) {
			new HablarSignals(hablar, loginXmpp.xmppSession, loginXmpp.privateStorageManager);
		}

		if (config.hasSound) {
			new HablarSoundSignals(hablar);
		}

		if (config.hasCopyToClipboard) {
			new HablarClipboard(hablar);
		}

		if (htmlConfig.hasLogger) {
			new HablarConsole(hablar, loginXmpp.xmppConnection, loginXmpp.xmppSession);
		}

		if (htmlConfig.hasLogin) {
			new HablarLogin(hablar, LoginConfig.getFromMeta(), loginXmpp.xmppSession);
		}

		if (htmlConfig.inline == null) {
			createDialog(widget, htmlConfig);
		} else {
			addHablarToDiv(widget, htmlConfig);
		}
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
