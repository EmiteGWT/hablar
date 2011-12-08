package com.calclab.hablar.client;

import java.util.HashMap;

import com.calclab.emite.browser.client.BrowserModule;
import com.calclab.emite.core.client.LoginXmpp;
import com.calclab.emite.core.client.conn.XmppConnection;
import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.presence.PresenceManager;
import com.calclab.emite.im.client.roster.SubscriptionHandler;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.emite.xep.chatstate.client.StateManager;
import com.calclab.emite.xep.muc.client.RoomManager;
import com.calclab.emite.xep.mucchatstate.client.MUCChatStateManager;
import com.calclab.emite.xep.mucdisco.client.RoomDiscoveryManager;
import com.calclab.emite.xep.search.client.SearchManager;
import com.calclab.emite.xep.storage.client.PrivateStorageManager;
import com.calclab.emite.xep.vcard.client.VCardManager;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules({ HablarModule.class, BrowserModule.class })
public interface HablarGinjector extends Ginjector {

	XmppConnection getXmppConnection();

	XmppSession getXmppSession();

	XmppRoster getXmppRoster();

	ChatManager getChatManager();

	RoomManager getRoomManager();

	StateManager getStateManager();

	RoomDiscoveryManager getRoomDiscoveryManager();

	MUCChatStateManager getMUCChatStateManager();

	PresenceManager getPresenceManager();

	SearchManager getSearchManager();

	VCardManager getVCardManager();

	SubscriptionHandler getSubscriptionHandler();

	PrivateStorageManager getPrivateStorageManager();
	
	HashMap <String, LoginXmpp> getLoginXmppMap();

}
