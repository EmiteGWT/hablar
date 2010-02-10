package com.calclab.hablar.core.client.container.main;

import static com.google.gwt.dom.client.Style.Unit.PX;

import com.calclab.hablar.core.client.HablarDisplay;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.pages.HeaderDisplay;
import com.google.gwt.user.client.ui.Widget;

public abstract class MainLayout {
    private final Widget container;

    public MainLayout(Widget container, HablarDisplay parent) {
	this.container = container;
	parent.add(container);
	parent.setWidgetLeftRight(container, 0, PX, 0, PX);
	parent.setWidgetTopBottom(container, 0, PX, 0, PX);
    }

    public abstract void add(Widget pageWidget, Widget headWidget);

    public abstract HeaderDisplay createHeaderDisplay(Page<?> page);

    public abstract void focus(Widget pageWidget);

    public Widget getWidget() {
	return container;
    }

}
