package com.calclab.hablar.client.search;

import java.util.HashMap;
import java.util.List;

import com.calclab.emite.core.client.xmpp.session.ResultListener;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.xep.search.client.SearchManager;
import com.calclab.emite.xep.search.client.SearchResultItem;
import com.calclab.hablar.client.search.SearchView.Level;
import com.calclab.hablar.client.ui.lists.ListItemView;
import com.calclab.hablar.client.ui.lists.ListLogic;
import com.calclab.hablar.client.ui.menu.MenuAction;
import com.calclab.hablar.client.ui.menu.PopupMenuView;
import com.calclab.suco.client.Suco;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.UIObject;

public class SearchLogic implements ListLogic {
    private final SearchView view;
    private final SearchManager manager;
    private final Roster roster;
    private PopupMenuView<SearchResultView> addToRosterMenu;
    private PopupMenuView<SearchResultView> removeFromRosterMenu;

    public SearchLogic(SearchView view) {
	this.view = view;
	manager = Suco.get(SearchManager.class);
	roster = Suco.get(Roster.class);
	createMenus();
    }

    @Override
    public void onItemClick(ListItemView view, Event event) {
    }

    @Override
    public void onMenuClicked(ListItemView view, UIObject ui) {
	SearchResultView resultView = (SearchResultView) view;
	boolean addToRoster = roster.getItemByJID(resultView.getItem().getJid()) == null;
	PopupMenuView<SearchResultView> menu = addToRoster ? addToRosterMenu : removeFromRosterMenu;
	menu.setTarget(resultView);
	menu.showRelativeToMenu(ui);
    }

    @Override
    public void onMouseOver(ListItemView view, boolean over) {
	view.setSelected(over);
	view.setMenuVisible(true);
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
		    view.addResult(item);
		}
	    }
	});
    }

    @SuppressWarnings("unchecked")
    private void createMenus() {
	addToRosterMenu = view.createMenu(new MenuAction<SearchResultView>("Add to roster") {
	    @Override
	    public void execute(SearchResultView target) {
		onResultToRoster(target);
	    }
	}, new MenuAction<SearchResultView>("Chat") {
	    @Override
	    public void execute(SearchResultView target) {
		onChatWith(target);
	    }
	});
	removeFromRosterMenu = view.createMenu(new MenuAction<SearchResultView>("Remove from roster") {
	    @Override
	    public void execute(SearchResultView target) {
		onRemoveFromRoster(target);
	    }
	}, new MenuAction<SearchResultView>("Chat") {
	    @Override
	    public void execute(SearchResultView target) {
		onChatWith(target);
	    }
	});

    }

    void onChatWith(SearchResultView result) {
	Suco.get(ChatManager.class).open(result.getItem().getJid());
    }

    void onRemoveFromRoster(SearchResultView result) {
	roster.removeItem(result.getItem().getJid());
    }

    void onResultToRoster(SearchResultView result) {
	SearchResultItem item = result.getItem();
	roster.requestAddItem(item.getJid(), item.getNick());
    }
}
