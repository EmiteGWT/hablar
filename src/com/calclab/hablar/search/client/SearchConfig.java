package com.calclab.hablar.search.client;

import com.calclab.emite.browser.client.PageAssist;

public class SearchConfig {
    public static SearchConfig getFromMeta() {
	final SearchConfig config = new SearchConfig();
	config.searchCloseable = PageAssist.isMetaTrue("hablar.searchCloseable");
	config.searchOnRoster = PageAssist.isMetaTrue("hablar.searchOnRoster");
	config.searchService = PageAssist.getMeta("emite.searchHost");
	return config;
    }

    /**
     * The name of the search service
     */
    public String searchService = "search.localhost";

    /**
     * Show search button in roster
     */
    public boolean searchOnRoster;

    /**
     * If search is closeable, it will have a close button
     */
    public boolean searchCloseable;

}
