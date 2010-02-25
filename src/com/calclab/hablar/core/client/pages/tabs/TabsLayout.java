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

    private final TabsMenuPresenter tabsMenuPresenter;

    public TabsLayout(final HablarDisplay parent) {
	super(tabs = new TabsPanel(BAR_SIZE, PX), parent);
	tabsMenuPresenter = new TabsMenuPresenter(tabs.getMenu(), this);
    }

    @Override
    public void add(final Widget pageWidget, final Widget headWidget) {
	tabs.add(pageWidget, headWidget);
    }

    @Override
    public HeaderDisplay createHeaderDisplay(final Page<?> page) {
	return new TabsHeaderWidget(page.getId());
    }

    @Override
    public void focus(final Widget pageWidget) {
	tabs.selectTab(pageWidget);
    }

    public TabsMenuPresenter getTabsMenuPresenter() {
	return tabsMenuPresenter;
    }

    @Override
    public void remove(final Widget pageWidget) {
	tabs.remove(pageWidget);
    }

}
