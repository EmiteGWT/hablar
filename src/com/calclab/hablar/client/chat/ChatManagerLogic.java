package com.calclab.hablar.client.chat;

import java.util.HashMap;

import com.calclab.emite.browser.client.PageAssist;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.hablar.client.ui.pages.Pages;
import com.calclab.hablar.client.ui.pages.Page.Visibility;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;

public class ChatManagerLogic {
    private final HashMap<Chat, ChatPage> widgets;

    public ChatManagerLogic(final Pages pages) {
	this.widgets = new HashMap<Chat, ChatPage>();

	ChatManager chatManager = Suco.get(ChatManager.class);
	String chatURI = PageAssist.getMeta("hablar.chatWidget");
	if (chatURI != null) {
	    chatManager.open(XmppURI.uri(chatURI));
	}

	chatManager.onChatCreated(new Listener<Chat>() {
	    @Override
	    public void onEvent(Chat chat) {
		GWT.log("HABLAR ChatManager - CREATE", null);
		ChatPage widget = new ChatPage(chat);
		widgets.put(chat, widget);
		pages.add(widget, Visibility.closed);
	    }
	});

	chatManager.onChatOpened(new Listener<Chat>() {
	    @Override
	    public void onEvent(Chat chat) {
		GWT.log("HABLAR ChatManager - OPEN", null);
		ChatPage widget = widgets.get(chat);
		assert widget != null;
		pages.open(widget);
	    }
	});

    }

}
