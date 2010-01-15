package com.calclab.hablar.client.search;

import com.calclab.emite.xep.search.client.SearchResultItem;
import com.calclab.hablar.client.ui.lists.ListItemWiget;
import com.calclab.hablar.client.ui.styles.HablarStyles;
import com.calclab.hablar.client.ui.styles.HablarStyles.IconType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class SearchResultItemWidget extends ListItemWiget implements SearchResultView {

    interface SearchResultUiBinder extends UiBinder<Widget, SearchResultItemWidget> {
    }

    private static SearchResultUiBinder uiBinder = GWT.create(SearchResultUiBinder.class);

    @UiField
    Label name, jid, menu;

    private SearchResultItem item;

    public SearchResultItemWidget(SearchLogic logic, SearchResultItem item) {
	super(logic);
	initWidget(uiBinder.createAndBindUi(this));
	menu.addStyleName(HablarStyles.get(HablarStyles.IconType.menu));
	setItem(item);
	setMenuVisible(false);
    }

    @Override
    public SearchResultItem getItem() {
	return item;
    }

    @UiHandler("menu")
    public void onMenu(ClickEvent evt) {
	logic.onMenuClicked(this, menu);
    }

    @Override
    public void setMenuVisible(boolean visible) {
	menu.setVisible(visible);
    }

    private void setItem(SearchResultItem item) {
	this.item = item;
	this.name.setText(item.getNick());
	this.jid.setText(item.getJid().toString());
    }

}
