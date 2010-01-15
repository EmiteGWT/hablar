package com.calclab.hablar.client.search;

import com.calclab.emite.xep.search.client.SearchResultItem;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class SearchResult extends Composite implements SearchResultView {

    interface SearchResultUiBinder extends UiBinder<Widget, SearchResult> {
    }

    private static SearchResultUiBinder uiBinder = GWT.create(SearchResultUiBinder.class);

    @UiField
    Label name;

    private final SearchResultItem item;

    private final SearchLogic logic;

    public SearchResult(SearchLogic logic, SearchResultItem item) {
	this.logic = logic;
	this.item = item;
	initWidget(uiBinder.createAndBindUi(this));
	this.name.setText(item.getNick());
	sinkEvents(Event.ONCLICK);
    }

    @Override
    public SearchResultItem getItem() {
	return item;
    }

    @Override
    public void onBrowserEvent(Event event) {
	if (event.getTypeInt() == Event.ONCLICK) {
	    logic.onResultClicked(this, event.getClientX(), event.getClientY());
	}
    }

}
