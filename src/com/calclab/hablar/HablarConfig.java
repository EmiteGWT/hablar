package com.calclab.hablar;

import com.calclab.emite.browser.client.PageAssist;
import com.calclab.hablar.basic.client.ui.HablarWidget;

public class HablarConfig {

    /**
     * Retrieve Hablar configuration from meta tags in html
     * 
     * @return
     */
    public static HablarConfig getFromMeta() {
	HablarConfig config = new HablarConfig();

	config.hasLogin = isTrueMeta("hablar.loginWidget");
	config.hasRoster = isTrueMeta("hablar.rosterWidget");
	config.hasSearch = isTrueMeta("hablar.searchWidget");
	config.hasSignals = isTrueMeta("hablar.hasSignals");
	config.hasChat = isTrueMeta("hablar.hasChats");

	config.dockRoster = PageAssist.getMeta("hablar.dockRoster");

	String layout = PageAssist.getMeta("hablar.layout");
	if ("tabs".equals(layout))
	    config.layout = HablarWidget.Layout.tabs;
	else
	    config.layout = HablarWidget.Layout.accordion;

	config.inline = PageAssist.getMeta("hablar.inline");
	config.width = PageAssist.getMeta("hablar.width");
	config.height = PageAssist.getMeta("hablar.height");
	if (config.width == null)
	    config.width = "400px";
	if (config.height == null)
	    config.height = "400px";
	return config;
    }

    // FIXME: implemented in PageAssist
    private static boolean isTrueMeta(String id) {
	return !"false".equals(PageAssist.getMeta(id));
    }

    /**
     * Has ChatModule
     */
    public boolean hasChat;

    /**
     * Dock the roster
     */
    public String dockRoster;

    /**
     * Choose a layout
     */
    public HablarWidget.Layout layout;

    /**
     * Show or not login panel
     */
    public boolean hasLogin;

    /**
     * Show or not roster panel
     */
    public boolean hasRoster;

    /**
     * Show or not search panel
     */
    public boolean hasSearch;

    /**
     * If not null, show 'hablar' inside the div with the id given
     */
    public String inline;

    /**
     * Width
     */
    public String width;

    /**
     * Height
     */
    public String height;

    /**
     * Has SignalModule
     */
    public boolean hasSignals;

}
