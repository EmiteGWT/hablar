package com.calclab.hablar.client.search;

import com.calclab.emite.xep.search.client.SearchResultItem;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class SearchResult extends Composite {

    interface SearchResultUiBinder extends UiBinder<Widget, SearchResult> {
    }

    private static SearchResultUiBinder uiBinder = GWT.create(SearchResultUiBinder.class);

    @UiField
    Label name;

    @UiField
    Anchor addToRoster;

    public SearchResult(SearchResultItem item) {
	initWidget(uiBinder.createAndBindUi(this));
	this.name.setText(item.getNick());
    }

    @UiHandler("addToRoster")
    public void onAddToRoster(ClickEvent e) {

    }

}
