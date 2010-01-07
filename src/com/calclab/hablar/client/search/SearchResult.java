package com.calclab.hablar.client.search;

import com.calclab.emite.xep.search.client.Item;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class SearchResult extends Composite {

    interface SearchResultUiBinder extends UiBinder<Widget, SearchResult> {
    }

    private static SearchResultUiBinder uiBinder = GWT.create(SearchResultUiBinder.class);

    @UiField
    Label name;

    public SearchResult(Item item) {
	initWidget(uiBinder.createAndBindUi(this));
	this.name.setText(item.getNick());
    }

}
