package com.calclab.hablar.chat.client;

import com.calclab.emite.browser.client.PageAssist;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;

public class ChatConfig {

    public static ChatConfig getFromMeta() {
	ChatConfig config = new ChatConfig();
	String chatURI = PageAssist.getMeta("hablar.chatWidget");
	config.uri = XmppURI.uri(chatURI);
	return config;
    }

    public XmppURI uri;

}
