package com.calclab.hablar.client.chat;

import java.util.HashMap;
import java.util.Set;

import com.calclab.emite.browser.client.PageAssist;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.client.ui.page.Page.Visibility;
import com.calclab.hablar.client.ui.pages.Pages;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;

public class ChatManagerLogic {
    private final HashMap<Chat, ChatPage> chatPages;
    private final Pages pages;
    private final Roster roster;

    public ChatManagerLogic(final Pages pages) {
	this.pages = pages;
	this.chatPages = new HashMap<Chat, ChatPage>();

	roster = Suco.get(Roster.class);
	final ChatManager chatManager = Suco.get(ChatManager.class);

	String chatURI = PageAssist.getMeta("hablar.chatWidget");
	if (chatURI != null) {
	    chatManager.open(XmppURI.uri(chatURI));
	}

	chatManager.onChatCreated(new Listener<Chat>() {
	    @Override
	    public void onEvent(Chat chat) {
		createChat(chat);
	    }
	});

	chatManager.onChatOpened(new Listener<Chat>() {
	    @Override
	    public void onEvent(Chat chat) {
		GWT.log("HABLAR ChatManager - OPEN", null);
		ChatPage widget = chatPages.get(chat);
		assert widget != null;
		pages.open(widget);
	    }
	});
	roster.onItemChanged(new Listener<RosterItem>() {
	    public void onEvent(RosterItem item) {
		XmppURI jid = item.getJID();
		Set<Chat> chats = chatPages.keySet();
		for (Chat chat : chats) {
		    if (chat.getURI().equalsNoResource(jid)) {
			chatPages.get(chat).setPresence(item.getShow());
		    }
		}
	    }
	});

    }

    private void createChat(Chat chat) {
	GWT.log("HABLAR ChatManager - CREATE", null);
	ChatPage chatPage = new ChatPage(chat);
	chatPages.put(chat, chatPage);
	pages.add(chatPage, Visibility.closed);
	RosterItem item = roster.getItemByJID(chat.getURI().getJID());
	Show show = item != null ? item.getShow() : Show.unknown;
	chatPage.setPresence(show);
    }

}
