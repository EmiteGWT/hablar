package com.calclab.hablar.search.client.query;

import java.util.HashMap;

import com.calclab.hablar.search.client.SearchQueryFactory;

/**
 * Implementation of {@link SearchQueryFactory} that creates a query that search
 * all the users whose nickname <em>contains all the words</em> in the search
 * term string.
 */
public class NicknameContainsOrderedWordsSearchQueryFactory implements SearchQueryFactory {

    /** {@inheritDoc} */
    @Override
    public HashMap<String, String> createSearchQuery(final String term) {
        final HashMap<String, String> query = new HashMap<String, String>();
        query.put("nick", "*" + term.trim().replaceAll("\\s+", "*") + "*");
        return query;
    }
}
