package com.calclab.hablar.chat.client;

import com.calclab.emite.browser.client.PageAssist;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;

/**
 * Configuration of chat module. It can be configured by html meta tags <br/>
 * Meta tags available:
 * 
 * "hablar.chatWidget"
 * 
 */
public class ChatConfig {

    public static ChatConfig getFromMeta() {
	ChatConfig config = new ChatConfig();
	String chatURI = PageAssist.getMeta("hablar.chatWidget");
	config.openChat = XmppURI.uri(chatURI);
	config.sendButtonVisible = isTrueMeta("hablar.sendButton");
	return config;
    }

    // FIXME: implemented in PageAssist
    private static boolean isTrueMeta(String id) {
	return !"false".equals(PageAssist.getMeta(id));
    }

    /**
     * Hablar module will open automatically this chat page when load
     */
    public XmppURI openChat;
    /**
     * A Send button in the chat pages
     */
    public boolean sendButtonVisible = true;

}
