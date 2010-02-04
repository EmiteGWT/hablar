package com.calclab.hablar.search.client;

import com.calclab.emite.xep.search.client.SearchResultItem;
import com.calclab.hablar.core.client.mvp.Presenter;

public class SearchResultItemPresenter implements Presenter<SearchResultItemDisplay> {

    private final SearchResultItemDisplay display;
    private final SearchResultItem item;

    public SearchResultItemPresenter(SearchResultItem item, SearchResultItemDisplay display) {
	this.item = item;
	this.display = display;
	display.setItem(item);
    }

    @Override
    public SearchResultItemDisplay getDisplay() {
	return display;
    }

    public SearchResultItem getItem() {
	return item;
    }

}
