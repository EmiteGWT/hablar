package com.calclab.hablar;

import com.calclab.emite.browser.client.PageAssist;
import com.calclab.hablar.core.client.HablarDisplay;
import com.calclab.hablar.rooms.client.HablarRoomsConfig;

public class HablarConfig {

    /**
     * Retrieve Hablar configuration from meta tags in html
     */
    public static HablarConfig getFromMeta() {
	final HablarConfig config = new HablarConfig();

	config.hasRoster = PageAssist.isMetaTrue("hablar.roster");
	config.hasSearch = PageAssist.isMetaTrue("hablar.search");
	config.hasSignals = PageAssist.isMetaTrue("hablar.hasSignals");
	config.hasChat = PageAssist.isMetaTrue("hablar.hasChats");

	config.dockRoster = PageAssist.getMeta("hablar.dockRoster");

	final String layout = PageAssist.getMeta("hablar.layout");
	if ("tabs".equals(layout)) {
	    config.layout = HablarDisplay.Layout.tabs;
	} else {
	    config.layout = HablarDisplay.Layout.accordion;
	}

	config.roomsConfig = HablarRoomsConfig.getFromMeta();
	return config;
    }

    HablarRoomsConfig roomsConfig = new HablarRoomsConfig();

    /**
     * Has ChatModule
     */
    public boolean hasChat = true;

    /**
     * Dock the roster: "left" or "right"
     */
    public String dockRoster = "left";

    /**
     * Choose a layout
     */
    public HablarDisplay.Layout layout = HablarDisplay.Layout.tabs;

    /**
     * Install Roster module
     */
    public boolean hasRoster = true;

    /**
     * Install Search module
     */
    public boolean hasSearch = true;

    /**
     * Install signals module
     */
    public boolean hasSignals = true;

}
