package com.calclab.hablar.search.client;

import java.util.HashMap;
import java.util.List;

import com.calclab.emite.core.client.xmpp.session.ResultListener;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.xep.search.client.SearchManager;
import com.calclab.emite.xep.search.client.SearchResultItem;
import com.calclab.hablar.basic.client.i18n.Msg;
import com.calclab.hablar.basic.client.ui.lists.ListItemView;
import com.calclab.hablar.basic.client.ui.lists.ListLogic;
import com.calclab.hablar.basic.client.ui.menu.MenuAction;
import com.calclab.hablar.basic.client.ui.menu.PopupMenuView;
import com.calclab.hablar.search.client.SearchPageView.Level;
import com.calclab.suco.client.Suco;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.UIObject;

public class SearchPageLogic implements ListLogic {
    public static final String ADD_ROSTER_MENU_DEB_ID = "SearchLogic-add-menu";
    public static final String REMOVE_ROSTER_MENU_DEB_ID = "SearchLogic-remove-menu";
    public static final String CHAT_DEB_ID = "SearchLogic-chat";
    public static final String ADD_ROSTERITEM_DEB_ID = "SearchLogic-add-item";
    public static final String REMOVE_ROSTERITEM_DEB_ID = "SearchLogic-remove-item";

    private final SearchPageView view;
    private final SearchManager manager;
    private final Roster roster;
    private PopupMenuView<SearchResultItemView> addToRosterMenu;
    private PopupMenuView<SearchResultItemView> removeFromRosterMenu;
    private final Msg i18n;

    public SearchPageLogic(final SearchPageView view) {
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
	final SearchResultItemView resultView = (SearchResultItemView) view;
	final boolean addToRoster = roster.getItemByJID(resultView.getItem().getJid()) == null;
	final PopupMenuView<SearchResultItemView> menu = addToRoster ? addToRosterMenu : removeFromRosterMenu;
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

    private void createMenus() {
	addToRosterMenu = view.createMenu(ADD_ROSTER_MENU_DEB_ID);
	addToRosterMenu.addAction(new MenuAction<SearchResultItemView>(i18n.addToContacts(), ADD_ROSTERITEM_DEB_ID) {
	    @Override
	    public void execute(final SearchResultItemView target) {
		onResultToRoster(target);
	    }
	});
	addToRosterMenu.addAction(new MenuAction<SearchResultItemView>(i18n.chat(), CHAT_DEB_ID) {
	    @Override
	    public void execute(final SearchResultItemView target) {
		onChatWith(target);
	    }
	});

	removeFromRosterMenu = view.createMenu(REMOVE_ROSTER_MENU_DEB_ID);
	removeFromRosterMenu.addAction(new MenuAction<SearchResultItemView>("Remove from roster",
		REMOVE_ROSTERITEM_DEB_ID) {
	    @Override
	    public void execute(final SearchResultItemView target) {
		onRemoveFromRoster(target);
	    }
	});
	removeFromRosterMenu.addAction(new MenuAction<SearchResultItemView>("Chat", CHAT_DEB_ID) {
	    @Override
	    public void execute(final SearchResultItemView target) {
		onChatWith(target);
	    }
	});

    }

    void onChatWith(final SearchResultItemView result) {
	Suco.get(ChatManager.class).open(result.getItem().getJid());
    }

    void onRemoveFromRoster(final SearchResultItemView result) {
	roster.removeItem(result.getItem().getJid());
    }

    void onResultToRoster(final SearchResultItemView result) {
	final SearchResultItem item = result.getItem();
	roster.requestAddItem(item.getJid(), item.getNick());
    }
}
