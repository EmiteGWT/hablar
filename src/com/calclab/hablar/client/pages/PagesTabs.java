package com.calclab.hablar.client.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class PagesTabs extends PagesWidget {

    interface PagesTabsUiBinder extends UiBinder<Widget, PagesTabs> {
    }

    interface TabStyles extends HeaderStyles {

    }

    private static PagesTabsUiBinder uiBinder = GWT.create(PagesTabsUiBinder.class);

    @UiField
    TabLayoutPanel tabs;

    @UiField
    TabStyles headerStyle;

    public PagesTabs() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void remove(PageWidget page) {
	tabs.remove(page);
    }

    @Override
    protected void addPage(PageWidget page) {
	PageHeader header = page.getHeader();
	header.setStyles(headerStyle);
	tabs.add(page, header);
    }

    @Override
    protected void showPage(PageWidget page) {
	int index = tabs.getWidgetIndex(page);
	tabs.selectTab(index);
    }

}
