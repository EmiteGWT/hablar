package com.calclab.hablar.client;

import com.calclab.emite.browser.client.PageAssist;

public class HablarConfig {

    public static enum Layout {
	accordion, tabs
    }

    /**
     * Retrieve Hablar configuration from meta tags in html
     * 
     * @return
     */
    public static HablarConfig getFromMeta() {
	HablarConfig config = new HablarConfig();
	config.hasLogin = !"false".equals(PageAssist.getMeta("hablar.loginWidget"));
	config.hasRoster = !"false".equals(PageAssist.getMeta("hablar.rosterWidget"));
	config.hasSearch = !"false".equals(PageAssist.getMeta("hablar.searchWidget"));

	config.dockRoster = PageAssist.getMeta("hablar.dockRoster");

	String layout = PageAssist.getMeta("hablar.layout");
	if ("tabs".equals(layout))
	    config.layout = HablarConfig.Layout.tabs;
	else
	    config.layout = HablarConfig.Layout.accordion;

	config.inline = PageAssist.getMeta("hablar.inline");
	config.width = PageAssist.getMeta("hablar.width");
	config.height = PageAssist.getMeta("hablar.height");
	if (config.width == null)
	    config.width = "400px";
	if (config.height == null)
	    config.height = "400px";
	return config;
    }

    /**
     * Dock the roster
     */
    public String dockRoster;

    /**
     * Choose a layout
     */
    public Layout layout;

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

}
