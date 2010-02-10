package com.calclab.hablar.core.client.pages.tabs;

import static com.google.gwt.dom.client.Style.Unit.PX;

import com.calclab.hablar.core.client.HablarDisplay;
import com.calclab.hablar.core.client.container.main.MainLayout;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.pages.HeaderDisplay;
import com.google.gwt.user.client.ui.Widget;

public class TabsLayout extends MainLayout {

    private static final double BAR_SIZE = 24;
    private static TabsPanel tabs;

    public TabsLayout(HablarDisplay parent) {
	super(tabs = new TabsPanel(BAR_SIZE, PX), parent);
    }

    @Override
    public void add(Widget pageWidget, Widget headWidget) {
	tabs.add(pageWidget, headWidget);
    }

    @Override
    public HeaderDisplay createHeaderDisplay(Page<?> page) {
	return new TabsHeaderWidget(page.getId());
    }

    @Override
    public void focus(Widget pageWidget) {
	tabs.selectTab(pageWidget);
    }

}
