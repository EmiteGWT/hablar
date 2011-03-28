package com.calclab.hablar.search.client.page;

import static com.calclab.hablar.search.client.HablarSearch.i18n;

import java.util.HashMap;
import java.util.List;

import com.calclab.emite.core.client.xmpp.session.ResultListener;
import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
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
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;

/**
 * SearchPage presenter.
 */
public class SearchPage extends PagePresenter<SearchDisplay> {
    private static final String SEARCH_MENU = "SearchMenu";
    private static int index = 0;
    private final SearchManager searchManager;
    private final Menu<SearchResultItem> itemMenu;
    private final SearchQueryFactory queryFactory;
    private final XmppSession session;

    public SearchPage(final XmppSession session, final SearchManager searchManager, final Visibility visibility,
	    final boolean closeable, final SearchQueryFactory queryFactory, final HablarEventBus eventBus,
	    final SearchWidget display) {
	super("HablarSearch", "" + ++index, eventBus, display);
	this.session = session;
	this.searchManager = searchManager;
	this.queryFactory = queryFactory;
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
	display.getSearchBox().addKeyDownHandler(new KeyDownHandler() {
	    @Override
	    public void onKeyDown(final KeyDownEvent event) {
		if (event.getNativeKeyCode() == 13) {
		    event.stopPropagation();
		    event.preventDefault();
		    search();
		}
	    }
	});
    }

    private void search() {
	final String text = display.getSearchTerm().getText().trim();
	if (text.length() > 0) {
	    display.clearResults();
	    display.showMessage(i18n().searchingTerm(text), Level.info);
	    final HashMap<String, String> query = queryFactory.createSearchQuery(text);

	    searchManager.search(query, new ResultListener<List<SearchResultItem>>() {
		@Override
		public void onFailure(final String message) {
		    display.showMessage(i18n().searchError(), Level.error);
		}

		@Override
		public void onSuccess(final List<SearchResultItem> items) {
		    final XmppURI currentUri = session.getCurrentUserURI();
		    int resultCount = 0;
		    for (final SearchResultItem item : items) {
			if (!currentUri.equalsNoResource(item.getJid())) {
			    resultCount++;
			    final SearchResultItemDisplay itemDisplay = display.newSearchResultItemDisplay(Idify
				    .id(item.getJid()));
			    new SearchResultItemPresenter(item, itemMenu, itemDisplay);
			    display.addResult(itemDisplay);
			}
		    }
		    display.showMessage(i18n().searchResultsFor(text, resultCount), Level.success);
		}
	    });
	    display.getSearchTerm().setText("");
	    display.getSearchFocus().setFocus(true);
	}
    }

}
