package com.calclab.hablar.search.client;

import com.calclab.emite.xep.search.client.SearchResultItem;
import com.calclab.hablar.basic.client.ui.menu.PopupMenuView;
import com.calclab.hablar.basic.client.ui.page.PageView;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasText;

public interface SearchPageView extends PageView {

    public static enum Level {
	info, error, success
    }

    void addResult(SearchResultItem item);

    void clearResults();

    PopupMenuView<SearchResultItemView> createMenu(String debugId);

    HasClickHandlers getSearchButton();

    HasChangeHandlers getSearchChange();

    Focusable getSearchFocus();

    HasText getSearchTerm();

    void showMessage(String body, Level level);

}
