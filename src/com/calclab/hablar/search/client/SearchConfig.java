package com.calclab.hablar.search.client;


import com.calclab.emite.browser.client.PageAssist;
import com.calclab.hablar.search.client.query.NicknameStartsWithSearchQueryFactory;

public class SearchConfig {

    public static SearchConfig getFromMeta() {
	final SearchConfig config = new SearchConfig();
	config.searchCloseable = PageAssist.isMetaTrue("hablar.searchCloseable");
	config.searchOnRoster = PageAssist.isMetaTrue("hablar.searchOnRoster");
	config.showSearchPageOnEmptyRoster = PageAssist.isMetaTrue("hablar.showSearchPageOnEmptyRoster", false);
	config.searchService = PageAssist.getMeta("emite.searchHost");
	return config;
    }

    SearchQueryFactory DEFAULT_QUERY_FACTORY = new NicknameStartsWithSearchQueryFactory();

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

    public boolean showSearchPageOnEmptyRoster = false;

    public SearchQueryFactory queryFactory = DEFAULT_QUERY_FACTORY;

    public SearchBasicActions searchActions;
}
