package com.calclab.hablar.roster.client;

import com.calclab.emite.browser.client.PageAssist;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;

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

	/**
	 * The default action for click
	 */
	public SimpleAction<RosterItem> rosterItemClickAction;
	
	/**
	 * The actions for the roster menu
	 */
	public RosterBasicActions rosterMenuActions;
}
