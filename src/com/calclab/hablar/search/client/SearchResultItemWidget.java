package com.calclab.hablar.search.client;

import com.calclab.emite.xep.search.client.SearchResultItem;
import com.calclab.hablar.basic.client.ui.icons.HablarIcons;
import com.calclab.hablar.basic.client.ui.lists.ListItemWidget;
import com.calclab.hablar.basic.client.ui.utils.DebugId;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class SearchResultItemWidget extends ListItemWidget implements SearchResultItemView {

    interface SearchResultUiBinder extends UiBinder<Widget, SearchResultItemWidget> {
    }

    public static final String SEARCHRESULT_ITEM_MENU_DEB_ID = "SearchResultItemWidget-item-menu-";
    public static final String SEARCHRESULT_ITEM_NAME_DEB_ID = "SearchResultItemWidget-item-name-";

    private static SearchResultUiBinder uiBinder = GWT.create(SearchResultUiBinder.class);

    @UiField
    Label name, jid, menu;

    private SearchResultItem item;

    public SearchResultItemWidget(final SearchPageLogic logic, final SearchResultItem item) {
	super(logic);
	initWidget(uiBinder.createAndBindUi(this));
	menu.addStyleName(HablarIcons.get(HablarIcons.IconType.menu));
	setItem(item);
	setMenuVisible(false);
    }

    @Override
    public SearchResultItem getItem() {
	return item;
    }

    @UiHandler("menu")
    public void onMenu(final ClickEvent evt) {
	logic.onMenuClicked(this, menu);
    }

    @Override
    public void setMenuVisible(final boolean visible) {
	menu.setVisible(visible);
    }

    private void setItem(final SearchResultItem item) {
	this.item = item;
	this.name.setText(item.getNick());
	this.jid.setText(item.getJid().toString());
	this.menu.ensureDebugId(DebugId.getFromJid(SEARCHRESULT_ITEM_MENU_DEB_ID, item.getJid()));
	this.name.ensureDebugId(DebugId.getFromJid(SEARCHRESULT_ITEM_NAME_DEB_ID, item.getJid()));
    }

}
