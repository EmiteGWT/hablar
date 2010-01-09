package com.calclab.hablar.client.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class PagesTabs extends PagesWidget {

    interface PagesTabsUiBinder extends UiBinder<Widget, PagesTabs> {
    }

    private static PagesTabsUiBinder uiBinder = GWT.create(PagesTabsUiBinder.class);

    @UiField
    TabLayoutPanel tabs;

    public PagesTabs() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void remove(PageWidget page) {
	tabs.remove(page);
    }

    @Override
    protected void addPage(PageWidget page) {
	tabs.add(page, page.getHeader());
    }

    @Override
    protected void showPage(PageWidget page) {
	int index = tabs.getWidgetIndex(page);
	tabs.selectTab(index);
    }

}
