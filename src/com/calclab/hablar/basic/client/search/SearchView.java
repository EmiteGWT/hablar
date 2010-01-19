package com.calclab.hablar.basic.client.search;

import com.calclab.emite.xep.search.client.SearchResultItem;
import com.calclab.hablar.basic.client.ui.menu.MenuAction;
import com.calclab.hablar.basic.client.ui.menu.PopupMenuView;
import com.calclab.hablar.basic.client.ui.page.Page;

public interface SearchView extends Page {

    public static enum Level {
	info, error, success
    }

    void addResult(SearchResultItem item);

    void clearResults();

    PopupMenuView<SearchResultView> createMenu(String debugId, MenuAction<SearchResultView>... actions);

    void showMessage(String body, Level level);

}
