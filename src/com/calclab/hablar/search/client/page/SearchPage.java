package com.calclab.hablar.search.client.page;

import static com.calclab.hablar.search.client.HablarSearch.i18n;

import java.util.HashMap;
import java.util.List;

import com.calclab.emite.core.client.xmpp.session.ResultListener;
import com.calclab.emite.xep.search.client.SearchManager;
import com.calclab.emite.xep.search.client.SearchResultItem;
import com.calclab.hablar.core.client.Idify;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.icon.Icons;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.core.client.ui.menu.MenuDisplay;
import com.calclab.hablar.search.client.SearchQueryFactory;
import com.calclab.hablar.search.client.page.SearchDisplay.Level;
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

    private final SearchQueryFactory queryFactory;

    public SearchPage(final Visibility visibility, final boolean closeable, final SearchQueryFactory queryFactory,
	    final HablarEventBus eventBus, final SearchWidget display) {
	super("HablarSearch", "" + ++index, eventBus, display);
	this.queryFactory = queryFactory;
	manager = Suco.get(SearchManager.class);
	setVisibility(visibility);
	model.setCloseable(closeable);
	final String title = i18n().searchUsers();

	model.init(Icons.SEARCH, title, title);
	final MenuDisplay<SearchResultItem> menuDisplay = display.createMenu(SEARCH_MENU);
	itemMenu = new Menu<SearchResultItem>(menuDisplay);
	bind();
    }

    public Menu<SearchResultItem> getItemMenu() {
	return itemMenu;
    }

    @Override
    public void setVisibility(final PagePresenter.Visibility visibility) {
	super.setVisibility(visibility);
	if (visibility == Visibility.focused) {
	    display.focusInput();
	}
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

    private void search() {
	final String text = display.getSearchTerm().getText().trim();
	if (text.length() > 0) {
	    display.clearResults();
	    display.showMessage(i18n().searchingTerm(text), Level.info);
	    final HashMap<String, String> query = queryFactory.createSearchQuery(text);

	    manager.search(query, new ResultListener<List<SearchResultItem>>() {
		@Override
		public void onFailure(final String message) {
		    display.showMessage(i18n().searchError(), Level.error);
		}

		@Override
		public void onSuccess(final List<SearchResultItem> items) {
		    display.showMessage(i18n().searchResultsFor(text, items.size()), Level.success);
		    for (final SearchResultItem item : items) {
			final SearchResultItemDisplay itemDisplay = display.newSearchResultItemDisplay(Idify.id(item
				.getJid()));
			new SearchResultItemPresenter(item, itemMenu, itemDisplay);
			display.addResult(itemDisplay);
		    }
		}
	    });
	    display.getSearchTerm().setText("");
	    display.getSearchFocus().setFocus(true);
	}
    }

}
