package com.calclab.hablar.client.ui.pages;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class TabPages extends AbstractPages {

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
    public boolean hasPage(Page page) {
	return tabs.getWidgetIndex((Widget) page) != -1;
    }

    @Override
    public void hide(Page page) {
	tabs.remove((Widget) page);
    }

    @Override
    protected void addPage(Page page, Pages.Position position) {
	PageHeader header = page.getHeader();
	header.setStyles(headerStyle);
	tabs.add((Widget) page, header);
    }

    @Override
    protected void showPage(Page page) {
	int index = tabs.getWidgetIndex((Widget) page);
	tabs.selectTab(index);
    }

}
