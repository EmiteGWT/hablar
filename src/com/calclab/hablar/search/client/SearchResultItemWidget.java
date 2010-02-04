package com.calclab.hablar.search.client;

import com.calclab.emite.xep.search.client.SearchResultItem;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class SearchResultItemWidget extends Composite implements SearchResultItemDisplay {

    interface SearchResultItemWidgetUiBinder extends UiBinder<Widget, SearchResultItemWidget> {
    }
    public static final String SEARCHRESULT_ITEM_MENU_DEB_ID = "SearchResultItemWidget-item-menu-";

    public static final String SEARCHRESULT_ITEM_NAME_DEB_ID = "SearchResultItemWidget-item-name-";

    private static SearchResultItemWidgetUiBinder uiBinder = GWT.create(SearchResultItemWidgetUiBinder.class);

    @UiField
    Label name, jid, menu;

    public SearchResultItemWidget() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public void setItem(final SearchResultItem item) {
	this.name.setText(item.getNick());
	this.jid.setText(item.getJid().toString());
	// this.menu.ensureDebugId(DebugId.getFromJid(SEARCHRESULT_ITEM_MENU_DEB_ID,
	// item.getJid()));
	// this.name.ensureDebugId(DebugId.getFromJid(SEARCHRESULT_ITEM_NAME_DEB_ID,
	// item.getJid()));
    }

}
