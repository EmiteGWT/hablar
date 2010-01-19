package com.calclab.hablar.search.client;

import com.calclab.emite.xep.search.client.SearchResultItem;
import com.calclab.hablar.basic.client.ui.menu.MenuAction;
import com.calclab.hablar.basic.client.ui.menu.PopupMenuView;
import com.calclab.hablar.basic.client.ui.page.Page;

public interface SearchPageView extends Page {

    public static enum Level {
	info, error, success
    }

    void addResult(SearchResultItem item);

    void clearResults();

    PopupMenuView<SearchResultItemView> createMenu(String debugId, MenuAction<SearchResultItemView>... actions);

    void showMessage(String body, Level level);

}
