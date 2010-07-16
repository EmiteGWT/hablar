package com.calclab.hablar.search.client.page;

import com.calclab.hablar.core.client.ui.icon.Icons;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class SearchResultItemWidget extends Composite implements SearchResultItemDisplay {

    interface SearchResultItemWidgetUiBinder extends UiBinder<Widget, SearchResultItemWidget> {
    }

    private static SearchResultItemWidgetUiBinder uiBinder = GWT.create(SearchResultItemWidgetUiBinder.class);

    @UiField
    Label name, jid;

    @UiField
    Image buddyIcon, menu;

    public SearchResultItemWidget(final String itemId) {
	initWidget(uiBinder.createAndBindUi(this));
	menu.ensureDebugId(itemId + "-search-menu");
	Icons.set(menu, Icons.MENU);
	Icons.set(buddyIcon, Icons.BUDDY);
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public HasClickHandlers getBuddyIcon() {
	return buddyIcon;
    }

    @Override
    public HasClickHandlers getClickableJid() {
	return jid;
    }

    @Override
    public HasClickHandlers getClickableName() {
	return name;
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
