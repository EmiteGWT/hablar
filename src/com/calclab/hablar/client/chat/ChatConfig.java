package com.calclab.hablar.client.chat;

import com.calclab.emite.browser.client.PageAssist;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;

public class ChatConfig {

    public static ChatConfig getFromMeta() {
	ChatConfig config = new ChatConfig();
	String chatURI = PageAssist.getMeta("hablar.chatWidget");
	try {
	    config.uri = XmppURI.uri(chatURI);
	} catch (Exception e) {
	}

	return config;
    }

    public XmppURI uri;

}
