package com.calclab.hablar.client.search;

import java.util.HashMap;
import java.util.List;

import com.calclab.emite.core.client.xmpp.session.ResultListener;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.xep.search.client.SearchManager;
import com.calclab.emite.xep.search.client.SearchResultItem;
import com.calclab.hablar.client.search.SearchView.Level;
import com.calclab.suco.client.Suco;

public class SearchLogic {
    private final SearchView view;
    private final SearchManager manager;
    private final Roster roster;

    public SearchLogic(SearchView view) {
	this.view = view;
	manager = Suco.get(SearchManager.class);
	roster = Suco.get(Roster.class);
    }

    public void onResultToRoster(SearchResultView result) {
	SearchResultItem item = result.getItem();
	roster.requestAddItem(item.getJid(), item.getNick());
	result.setAddToRosterVisible(false);
    }

    public void search(final String text) {
	view.clearResults();
	view.showMessage("Searching " + text + "...", Level.info);
	HashMap<String, String> query = new HashMap<String, String>();
	query.put("nick", text + "*");

	manager.search(query, new ResultListener<List<SearchResultItem>>() {
	    @Override
	    public void onFailure(String message) {
		view.showMessage("Couldn't retrieve results", Level.error);
	    }

	    @Override
	    public void onSuccess(List<SearchResultItem> items) {
		view.showMessage("Results for '" + text + "'. " + items.size() + " users found.", Level.success);
		for (SearchResultItem item : items) {
		    boolean addToRoster = roster.getItemByJID(item.getJid()) == null;
		    view.addResult(item, addToRoster);
		}
	    }
	});
    }
}
