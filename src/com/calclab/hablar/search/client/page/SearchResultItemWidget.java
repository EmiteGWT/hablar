package com.calclab.hablar.search.client.page;

import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
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
	menu.addStyleName(HablarIcons.get(IconType.menu));
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasText getJid() {
	return jid;
    }

    @Override
    public HasClickHandlers getMenu() {
	return menu;
    }

    @Override
    public HasText getName() {
	return name;
    }

}
