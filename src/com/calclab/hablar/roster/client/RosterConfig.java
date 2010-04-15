package com.calclab.hablar.roster.client;

import com.calclab.emite.browser.client.PageAssist;

public class RosterConfig {
    public static RosterConfig getFromMeta() {
	final RosterConfig config = new RosterConfig();
	config.oneClickChat = PageAssist.isMetaTrue("hablar.oneClickChat");
	return config;
    }

    /**
     * If true, one click on the roster item opens a new chat.
     */
    public boolean oneClickChat;
}
