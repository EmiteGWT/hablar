package com.calclab.hablar.search.client;

import static com.calclab.hablar.core.client.i18n.Translator.i18n;

import java.util.HashMap;
import java.util.List;

import com.calclab.emite.core.client.xmpp.session.ResultListener;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.xep.search.client.SearchManager;
import com.calclab.emite.xep.search.client.SearchResultItem;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.ui.menu.PopupMenuView;
import com.calclab.hablar.search.client.SearchDisplay.Level;
import com.calclab.suco.client.Suco;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class SearchPage extends PagePresenter<SearchDisplay> {
    public static final String ADD_ROSTER_MENU_DEB_ID = "SearchLogic-add-menu";
    public static final String REMOVE_ROSTER_MENU_DEB_ID = "SearchLogic-remove-menu";
    public static final String CHAT_DEB_ID = "SearchLogic-chat";
    public static final String ADD_ROSTERITEM_DEB_ID = "SearchLogic-add-item";
    public static final String REMOVE_ROSTERITEM_DEB_ID = "SearchLogic-remove-item";
    private static int index = 0;

    private final SearchManager manager;
    private final Roster roster;

    private PopupMenuView<SearchResultItemPresenter> addToRosterMenu;
    private PopupMenuView<SearchResultItemPresenter> removeFromRosterMenu;

    public SearchPage(HablarEventBus eventBus, SearchWidget display) {
	super("HablarSearch", "" + (++index), eventBus, display);
	manager = Suco.get(SearchManager.class);
	roster = Suco.get(Roster.class);

	getState().init(HablarIcons.get(HablarIcons.IconType.search), i18n().searchUsers());
	createMenus();
	bind();
    }

    private void bind() {
	display.getSearchButton().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(ClickEvent event) {
		search();
	    }
	});
	display.getSearchChange().addChangeHandler(new ChangeHandler() {
	    @Override
	    public void onChange(ChangeEvent event) {
		search();
	    }
	});

    }

    private void createMenus() {
	addToRosterMenu = display.createMenu(ADD_ROSTER_MENU_DEB_ID);
	addToRosterMenu.addAction(new Action<SearchResultItemPresenter>(i18n().addToContacts(),
		ADD_ROSTERITEM_DEB_ID) {
	    @Override
	    public void execute(final SearchResultItemPresenter target) {
		onResultToRoster(target);
	    }
	});
	addToRosterMenu.addAction(new Action<SearchResultItemPresenter>(i18n().chat(), CHAT_DEB_ID) {
	    @Override
	    public void execute(final SearchResultItemPresenter target) {
		onChatWith(target);
	    }
	});

	removeFromRosterMenu = display.createMenu(REMOVE_ROSTER_MENU_DEB_ID);
	removeFromRosterMenu.addAction(new Action<SearchResultItemPresenter>("Remove from roster",
		REMOVE_ROSTERITEM_DEB_ID) {
	    @Override
	    public void execute(final SearchResultItemPresenter target) {
		onRemoveFromRoster(target);
	    }
	});
	removeFromRosterMenu.addAction(new Action<SearchResultItemPresenter>("Chat", CHAT_DEB_ID) {
	    @Override
	    public void execute(final SearchResultItemPresenter target) {
		onChatWith(target);
	    }
	});
    }

    private void search() {
	final String text = display.getSearchTerm().getText().trim();
	if (text.length() > 0) {
	    display.clearResults();
	    display.showMessage("Searching " + text + "...", Level.info);
	    final HashMap<String, String> query = new HashMap<String, String>();
	    query.put("nick", text + "*");

	    manager.search(query, new ResultListener<List<SearchResultItem>>() {
		@Override
		public void onFailure(final String message) {
		    display.showMessage("Couldn't retrieve results", Level.error);
		}

		@Override
		public void onSuccess(final List<SearchResultItem> items) {
		    display.showMessage(i18n().searchResultsFor(text, items.size()), Level.success);
		    for (final SearchResultItem item : items) {
			SearchResultItemDisplay itemDisplay = display.newSearchResultItemDisplay();
			new SearchResultItemPresenter(item, itemDisplay);
			display.addResult(itemDisplay);
		    }
		}
	    });
	    display.getSearchTerm().setText("");
	    display.getSearchFocus().setFocus(true);
	}
    }

    void onChatWith(final SearchResultItemPresenter result) {
	Suco.get(ChatManager.class).open(result.getItem().getJid());
    }

    void onRemoveFromRoster(final SearchResultItemPresenter result) {
	roster.removeItem(result.getItem().getJid());
    }

    void onResultToRoster(final SearchResultItemPresenter result) {
	final SearchResultItem item = result.getItem();
	roster.requestAddItem(item.getJid(), item.getNick());
    }

}
