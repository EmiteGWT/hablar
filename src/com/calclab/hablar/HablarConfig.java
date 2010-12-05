package com.calclab.hablar;

import com.calclab.emite.browser.client.PageAssist;
import com.calclab.hablar.chat.client.ChatConfig;
import com.calclab.hablar.core.client.HablarDisplay;
import com.calclab.hablar.core.client.pages.tabs.TabsLayout;
import com.calclab.hablar.core.client.pages.tabs.TabsLayout.TabHeaderSize;
import com.calclab.hablar.rooms.client.HablarRoomsConfig;
import com.calclab.hablar.roster.client.RosterConfig;
import com.calclab.hablar.search.client.SearchConfig;
import com.calclab.hablar.signals.sound.client.SoundSignalsConfig;
import com.calclab.hablar.vcard.client.VCardConfig;

public class HablarConfig {

    private static void createTabHeaderSize(final HablarConfig config) {
	Integer trim = null;
	final String trimString = PageAssist.getMeta("hablar.tabHeaderTrim");
	if (trimString != null) {
	    try {
		trim = Integer.decode(trimString);
	    } catch (final NumberFormatException e) {
		// Ignore it.
	    }
	}
	config.tabHeaderSize = TabHeaderSize.create(PageAssist.getMeta("hablar.tabHeaderHeight"), PageAssist
		.getMeta("hablar.tabHeaderWidth"), trim);
    }

    /**
     * Retrieve Hablar configuration from meta tags in html
     */
    public static HablarConfig getFromMeta() {
	final HablarConfig config = new HablarConfig();

	config.hasRoster = PageAssist.isMetaTrue("hablar.roster");
	config.hasSearch = PageAssist.isMetaTrue("hablar.search");
	config.hasSignals = PageAssist.isMetaTrue("hablar.hasSignals");
	config.hasChat = PageAssist.isMetaTrue("hablar.hasChats");
	config.hasVCard = PageAssist.isMetaTrue("hablar.hasVCard");
	config.hasCopyToClipboard = PageAssist.isMetaTrue("hablar.hasCopyToClipboard");
	config.hasSound = PageAssist.isMetaTrue("hablar.hasSound");

	config.dockRoster = PageAssist.getMeta("hablar.dockRoster");

	final String layout = PageAssist.getMeta("hablar.layout");
	if ("tabs".equals(layout)) {
	    config.layout = HablarDisplay.Layout.tabs;
	} else {
	    config.layout = HablarDisplay.Layout.accordion;
	}
	if (config.layout == HablarDisplay.Layout.tabs) {
	    createTabHeaderSize(config);
	}

	config.roomsConfig = HablarRoomsConfig.getFromMeta();
	config.rosterConfig = RosterConfig.getFromMeta();
	config.searchConfig = SearchConfig.getFromMeta();
	config.soundConfig = SoundSignalsConfig.getFromMeta();
	config.vcardConfig = VCardConfig.getFromMeta();
	config.chatConfig = ChatConfig.getFromMeta();
	return config;
    }

    public VCardConfig vcardConfig;

    /**
     * The Rooms configuration
     */
    public HablarRoomsConfig roomsConfig = new HablarRoomsConfig();

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
     * The size of the header in tabs layout
     */
    public TabsLayout.TabHeaderSize tabHeaderSize;

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

    /**
     * Show user page docked on top
     */
    public boolean dockUser = true;

    /**
     * The Search module configuration
     */
    public RosterConfig rosterConfig = new RosterConfig();

    /**
     * The Search module configuration
     */
    public SearchConfig searchConfig = new SearchConfig();

    /**
     * Install the copy-to-clipboard module
     */
    public boolean hasCopyToClipboard = true;

    /**
     * Install the VCard module
     */
    public boolean hasVCard = true;

    /**
     * Install SoundSignalModule
     */
    public boolean hasSound = true;

    public SoundSignalsConfig soundConfig = new SoundSignalsConfig();

    public ChatConfig chatConfig = new ChatConfig();
}
