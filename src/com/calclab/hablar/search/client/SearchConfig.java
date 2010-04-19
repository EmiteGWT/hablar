package com.calclab.hablar.search.client;

import java.util.HashMap;

import com.calclab.emite.browser.client.PageAssist;

public class SearchConfig {

    public static SearchConfig getFromMeta() {
	final SearchConfig config = new SearchConfig();
	config.searchCloseable = PageAssist.isMetaTrue("hablar.searchCloseable");
	config.searchOnRoster = PageAssist.isMetaTrue("hablar.searchOnRoster");
	config.searchService = PageAssist.getMeta("emite.searchHost");
	return config;
    }

    SearchQueryFactory DEFAULT_QUERY_FACTORY = new SearchQueryFactory() {
	@Override
	public HashMap<String, String> createSearchQuery(final String term) {
	    final HashMap<String, String> query = new HashMap<String, String>();
	    query.put("nick", term + "*");
	    return query;
	}
    };

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

    public SearchQueryFactory queryFactory = DEFAULT_QUERY_FACTORY;

}
