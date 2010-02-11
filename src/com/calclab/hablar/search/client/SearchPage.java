package com.calclab.hablar.search.client;

import static com.calclab.hablar.core.client.i18n.Translator.i18n;

import java.util.HashMap;
import java.util.List;

import com.calclab.emite.core.client.xmpp.session.ResultListener;
import com.calclab.emite.xep.search.client.SearchManager;
import com.calclab.emite.xep.search.client.SearchResultItem;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.core.client.ui.menu.MenuDisplay;
import com.calclab.hablar.search.client.SearchDisplay.Level;
import com.calclab.suco.client.Suco;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class SearchPage extends PagePresenter<SearchDisplay> {
    private static final String SEARCH_MENU = "SearchMenu";

    private static int index = 0;

    private final SearchManager manager;

    private final Menu<SearchResultItem> itemMenu;

    public SearchPage(final HablarEventBus eventBus, final SearchWidget display) {
	super("HablarSearch", "" + ++index, eventBus, display);
	manager = Suco.get(SearchManager.class);

	getState().init(HablarIcons.get(HablarIcons.IconType.search), i18n().searchUsers());
	final MenuDisplay<SearchResultItem> menuDisplay = display.createMenu(SEARCH_MENU);
	itemMenu = new Menu<SearchResultItem>(menuDisplay);
	bind();
    }

    private void bind() {
	display.getSearchButton().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		search();
	    }
	});
	display.getSearchChange().addChangeHandler(new ChangeHandler() {
	    @Override
	    public void onChange(final ChangeEvent event) {
		search();
	    }
	});

    }

    public Menu<SearchResultItem> getItemMenu() {
	return itemMenu;
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
			final SearchResultItemDisplay itemDisplay = display.newSearchResultItemDisplay();
			new SearchResultItemPresenter(item, itemDisplay);
			display.addResult(itemDisplay);
		    }
		}
	    });
	    display.getSearchTerm().setText("");
	    display.getSearchFocus().setFocus(true);
	}
    }

}
