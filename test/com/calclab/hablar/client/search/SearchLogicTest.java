package com.calclab.hablar.client.search;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.calclab.emite.xep.search.client.SearchResultItem;
import com.calclab.hablar.client.chat.EmiteTester;
import com.calclab.hablar.client.search.SearchView.Level;

public class SearchLogicTest {
    private EmiteTester tester;
    private SearchView view;
    private SearchLogic logic;

    @Before
    public void before() {
	tester = new EmiteTester();
	view = mock(SearchView.class);
	logic = new SearchLogic(view);
    }

    @Test
    public void shouldSearchOnNick() {
	logic.search("myText");
	HashMap<String, String> query = tester.searchManager.getLastQuery();
	assertEquals("myText*", query.get("nick"));
    }

    @Test
    public void shouldShowMessageWhenSearching() {
	logic.search("anything");
	verify(view).showMessage(anyString(), same(Level.info));
    }

    @Test
    public void shoulwShowMessageIfSuccess() {
	logic.search("anything");
	tester.searchManager.fireSearchSuccess(new ArrayList<SearchResultItem>());
	verify(view).showMessage(anyString(), same(Level.success));

    }
}
