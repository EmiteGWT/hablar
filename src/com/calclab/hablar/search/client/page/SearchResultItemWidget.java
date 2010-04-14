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

    private static SearchResultItemWidgetUiBinder uiBinder = GWT.create(SearchResultItemWidgetUiBinder.class);

    @UiField
    Label buddyIcon;

    @UiField
    Label name, jid, menu;

    public SearchResultItemWidget(final String itemId) {
	initWidget(uiBinder.createAndBindUi(this));
	menu.addStyleName(HablarIcons.get(IconType.menu));
	menu.ensureDebugId(itemId + "-search-menu");
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
}
