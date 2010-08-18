package com.calclab.hablar.search.client.page;

import com.calclab.emite.xep.search.client.SearchResultItem;
import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.ui.menu.MenuDisplay;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasText;

public interface SearchDisplay extends Display {

	public static enum Level {
		info, error, success
	}

	void addResult(SearchResultItemDisplay item);

	void clearResults();

	MenuDisplay<SearchResultItem> createMenu(String debugId);

	void focusInput();

	HasKeyDownHandlers getSearchBox();

	HasClickHandlers getSearchButton();

	@Deprecated
	HasChangeHandlers getSearchChange();

	Focusable getSearchFocus();

	HasText getSearchTerm();

	SearchResultItemDisplay newSearchResultItemDisplay(String itemId);

	void showMessage(String body, Level level);
}
