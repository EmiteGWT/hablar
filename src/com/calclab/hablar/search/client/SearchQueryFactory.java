package com.calclab.hablar.search.client;

import java.util.HashMap;

import com.calclab.emite.xep.search.client.SearchManager;

/**
 * A pluggable abstraction to change the way a simple search query is built
 * (because the lack of a clear standard).
 */
public interface SearchQueryFactory {
    /**
     * Given the following term should return a valid query hashmap to use with
     * method search of SearchManager
     * 
     * @param term
     *            user-entered search term
     * @return a valid simple search query
     * 
     * @see SearchManager
     */
    HashMap<String, String> createSearchQuery(String term);
}
