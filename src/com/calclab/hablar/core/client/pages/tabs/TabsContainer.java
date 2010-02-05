package com.calclab.hablar.core.client.pages.tabs;

import static com.google.gwt.dom.client.Style.Unit.PX;

import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.pages.HeaderDisplay;
import com.calclab.hablar.core.client.pages.MainContainer;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class TabsContainer extends MainContainer {
    private static final double SIZE_HEADER = 24;

    public TabsContainer(LayoutPanel parent) {
	super(new TabLayoutPanel(SIZE_HEADER, PX), parent);
    }

    @Override
    public boolean unfocus(Page<?> page) {
	return false;
    }

    @Override
    protected void add(Widget container, Widget pageWidget, Widget headerWidget) {
	((TabLayoutPanel) container).add(pageWidget, headerWidget);
    }

    @Override
    protected HeaderDisplay createHeaderDisplay(Page<?> page) {
	return new TabsHeaderWidget(page.getId());
    }

    @Override
    protected void focus(Widget container, Widget pageWidget) {
	((TabLayoutPanel) container).selectTab(pageWidget);
    }

}
