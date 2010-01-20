package com.calclab.hablar.basic.client.ui.pages.panel;

import com.calclab.hablar.basic.client.ui.page.HeaderStyles;
import com.calclab.hablar.basic.client.ui.page.PageHeader;
import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.hablar.basic.client.ui.pages.PagesPanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class TabPages extends Composite implements PagesPanel {

    interface PagesTabsUiBinder extends UiBinder<Widget, TabPages> {
    }

    interface TabStyles extends HeaderStyles {

    }

    private static PagesTabsUiBinder uiBinder = GWT.create(PagesTabsUiBinder.class);

    @UiField
    TabLayoutPanel tabs;

    @UiField
    TabStyles headerStyle;

    public TabPages() {
	initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void addPageView(PageView pageView) {
	PageHeader header = pageView.getHeader();
	header.setStyles(headerStyle);
	tabs.add((Widget) pageView, (Widget) header);
    }

    @Override
    public boolean hasPageView(PageView pageView) {
	return tabs.getWidgetIndex((Widget) pageView) != -1;
    }

    @Override
    public void removePageView(PageView pageView) {
	tabs.remove((Widget) pageView);
    }

    @Override
    public void showPageView(PageView pageView) {
	int index = tabs.getWidgetIndex((Widget) pageView);
	tabs.selectTab(index);
    }

}
