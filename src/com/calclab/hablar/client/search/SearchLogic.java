package com.calclab.hablar.client.search;

import java.util.HashMap;
import java.util.List;

import com.calclab.emite.core.client.xmpp.session.ResultListener;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.xep.search.client.SearchManager;
import com.calclab.emite.xep.search.client.SearchResultItem;
import com.calclab.hablar.client.i18n.Msg;
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
    private final Msg i18n;

    public SearchLogic(final SearchView view) {
	this.view = view;
	manager = Suco.get(SearchManager.class);
	roster = Suco.get(Roster.class);
	i18n = Suco.get(Msg.class);
	createMenus();
    }

    @Override
    public void onItemClick(final ListItemView view, final Event event) {
    }

    @Override
    public void onMenuClicked(final ListItemView view, final UIObject ui) {
	final SearchResultView resultView = (SearchResultView) view;
	final boolean addToRoster = roster.getItemByJID(resultView.getItem().getJid()) == null;
	final PopupMenuView<SearchResultView> menu = addToRoster ? addToRosterMenu : removeFromRosterMenu;
	menu.setTarget(resultView);
	menu.showRelativeToMenu(ui);
    }

    @Override
    public void onMouseOver(final ListItemView view, final boolean over) {
	view.setSelected(over);
	view.setMenuVisible(over);
    }

    public void search(final String text) {
	view.clearResults();
	view.showMessage("Searching " + text + "...", Level.info);
	final HashMap<String, String> query = new HashMap<String, String>();
	query.put("nick", text + "*");

	manager.search(query, new ResultListener<List<SearchResultItem>>() {
	    @Override
	    public void onFailure(final String message) {
		view.showMessage("Couldn't retrieve results", Level.error);
	    }

	    @Override
	    public void onSuccess(final List<SearchResultItem> items) {
		view.showMessage(i18n.searchResultsFor(text, items.size()), Level.success);
		for (final SearchResultItem item : items) {
		    view.addResult(item);
		}
	    }
	});
    }

    void onChatWith(final SearchResultView result) {
	Suco.get(ChatManager.class).open(result.getItem().getJid());
    }

    void onRemoveFromRoster(final SearchResultView result) {
	roster.removeItem(result.getItem().getJid());
    }

    void onResultToRoster(final SearchResultView result) {
	final SearchResultItem item = result.getItem();
	roster.requestAddItem(item.getJid(), item.getNick());
    }

    @SuppressWarnings("unchecked")
    private void createMenus() {
	addToRosterMenu = view.createMenu(new MenuAction<SearchResultView>(i18n.addToContacts()) {
	    @Override
	    public void execute(final SearchResultView target) {
		onResultToRoster(target);
	    }
	}, new MenuAction<SearchResultView>(i18n.chat()) {
	    @Override
	    public void execute(final SearchResultView target) {
		onChatWith(target);
	    }
	});
	removeFromRosterMenu = view.createMenu(new MenuAction<SearchResultView>("Remove from roster") {
	    @Override
	    public void execute(final SearchResultView target) {
		onRemoveFromRoster(target);
	    }
	}, new MenuAction<SearchResultView>("Chat") {
	    @Override
	    public void execute(final SearchResultView target) {
		onChatWith(target);
	    }
	});

    }
}
