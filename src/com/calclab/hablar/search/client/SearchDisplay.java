package com.calclab.hablar.search.client;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.ui.menu.PopupMenuView;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasText;

public interface SearchDisplay extends Display {

    public static enum Level {
	info, error, success
    }

    void addResult(SearchResultItemDisplay item);

    void clearResults();

    PopupMenuView<SearchResultItemPresenter> createMenu(String debugId);

    HasClickHandlers getSearchButton();

    HasChangeHandlers getSearchChange();

    Focusable getSearchFocus();

    HasText getSearchTerm();

    SearchResultItemDisplay newSearchResultItemDisplay();

    void showMessage(String body, Level level);
}
