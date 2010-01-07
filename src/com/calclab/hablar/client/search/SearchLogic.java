package com.calclab.hablar.client.search;

import java.util.HashMap;
import java.util.List;

import com.calclab.emite.core.client.xmpp.session.ResultListener;
import com.calclab.emite.xep.search.client.Item;
import com.calclab.emite.xep.search.client.SearchManager;
import com.calclab.suco.client.Suco;

public class SearchLogic {
    private final SearchPage view;
    private final SearchManager manager;

    public SearchLogic(SearchPage view) {
	this.view = view;
	manager = Suco.get(SearchManager.class);
    }

    public void search(final String text) {
	view.clearResults();
	view.showMessage("Searching " + text + "...", view.style.info());
	HashMap<String, String> query = new HashMap<String, String>();
	query.put("nick", text + "*");

	manager.search(query, new ResultListener<List<Item>>() {
	    @Override
	    public void onFailure(String message) {
		view.showMessage("Couldn't retrieve results", view.style.alert());
	    }

	    @Override
	    public void onSuccess(List<Item> items) {
		view.showMessage("Results for '" + text + "'. " + items.size() + " users found.", view.style.success());
		for (Item item : items) {
		    view.addResult(item);
		}
	    }
	});
    }
}
