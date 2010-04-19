/**
 *
 */
package com.calclab.hablar.search.client.query;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.calclab.hablar.search.client.SearchQueryFactory;

/**
 * Tests {@link NicknameContainsSearchQueryFactory}.
 *
 */
public class NicknameContainsSearchQueryFactoryTest {

    /**
     * Test method for {@link NicknameContainsOrderedWordsSearchQueryFactory#createSearchQuery(java.lang.String)}.
     */
    @Test
    public void testCreateSearchQuery() {
	SearchQueryFactory factory = new NicknameContainsSearchQueryFactory();
	Map<String, String> map = new HashMap<String, String>();
	map.put("nick", "*term*");
	assertEquals(map, factory.createSearchQuery("term"));

	map.put("nick", "*    this   is     it   *");
	assertEquals(map, factory.createSearchQuery("    this   is     it   "));
    }

}
