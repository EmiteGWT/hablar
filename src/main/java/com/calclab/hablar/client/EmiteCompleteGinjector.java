package com.calclab.hablar.client;

import com.calclab.emite.browser.client.BrowserModule;
import com.calclab.emite.core.client.CoreModule;
import com.calclab.emite.core.client.conn.XmppConnection;
import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.im.client.ImModule;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.presence.PresenceManager;
import com.calclab.emite.im.client.roster.SubscriptionHandler;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.emite.xep.chatstate.client.ChatStateModule;
import com.calclab.emite.xep.chatstate.client.StateManager;
import com.calclab.emite.xep.disco.client.DiscoveryModule;
import com.calclab.emite.xep.muc.client.MucModule;
import com.calclab.emite.xep.muc.client.RoomManager;
import com.calclab.emite.xep.mucchatstate.client.MUCChatStateManager;
import com.calclab.emite.xep.mucchatstate.client.MucChatStateModule;
import com.calclab.emite.xep.mucdisco.client.MucDiscoveryModule;
import com.calclab.emite.xep.mucdisco.client.RoomDiscoveryManager;
import com.calclab.emite.xep.search.client.SearchManager;
import com.calclab.emite.xep.search.client.SearchModule;
import com.calclab.emite.xep.storage.client.PrivateStorageManager;
import com.calclab.emite.xep.storage.client.PrivateStorageModule;
import com.calclab.emite.xep.vcard.client.VCardManager;
import com.calclab.emite.xep.vcard.client.VCardModule;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;

@GinModules({ CoreModule.class, ImModule.class, MucModule.class,
		MucChatStateModule.class, ChatStateModule.class,
		PrivateStorageModule.class, MucDiscoveryModule.class,
		VCardModule.class, SearchModule.class, DiscoveryModule.class,
		BrowserModule.class })
public interface EmiteCompleteGinjector extends Ginjector {
	XmppSession getXmppSession();

	XmppConnection getXmppConnection();

	XmppRoster getXmppRoster();

	ChatManager getChatManager();

	RoomManager getRoomManager();

	StateManager getStateManager();

	MUCChatStateManager getMUCChatStateManager();

	PresenceManager getPresenceManager();

	SubscriptionHandler getSubscriptionHandler();

	PrivateStorageManager getPrivateStorageManager();

	RoomDiscoveryManager getRoomDiscoveryManager();

	VCardManager getVCardManager();

	SearchManager getSearchManager();
}
