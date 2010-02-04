package com.calclab.hablar.core.client.pages;

import static com.google.gwt.dom.client.Style.Unit.PX;

import java.util.HashMap;

import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public abstract class MainContainer implements PagesContainer {
    private static class PageAndHead {
	public final Widget pageWidget;
	public final Widget headWidget;

	public PageAndHead(Widget pageWidget, Widget headWidget) {
	    this.pageWidget = pageWidget;
	    this.headWidget = headWidget;
	}

    }

    public static final String ROL = "Main";
    private final Widget container;

    private final HashMap<Page<?>, PageAndHead> pages;

    public MainContainer(Widget container, LayoutPanel parent) {
	this.container = container;
	this.pages = new HashMap<Page<?>, PageAndHead>();
	parent.add(container);
	parent.setWidgetLeftRight(container, 0, PX, 0, PX);
	parent.setWidgetTopBottom(container, 0, PX, 0, PX);
	parent.forceLayout();
    }

    @Override
    public boolean add(Page<?> page) {
	HeaderDisplay headerDisplay = createHeaderDisplay(page);
	HeaderPresenter header = new HeaderPresenter(page.getState(), headerDisplay);
	Widget pageWidget = page.getDisplay().asWidget();
	Widget headWidget = header.getDisplay().asWidget();
	pages.put(page, new PageAndHead(pageWidget, headWidget));
	if (page.getState().getVisibility() != Visibility.hidden) {
	    add(container, pageWidget, headWidget);
	}
	return true;
    }

    @Override
    public boolean focus(Page<?> page) {
	PageAndHead widgets = getWidgets(page);
	if (widgets != null) {
	    if (page.getState().getVisibility() == Visibility.hidden) {
		add(container, widgets.pageWidget, widgets.headWidget);
	    }
	    focus(container, widgets.pageWidget);
	    return true;
	}
	return false;
    }

    @Override
    public String getRol() {
	return ROL;
    }

    @Override
    public Widget getWidget() {
	return container;
    }

    @Override
    public boolean hide(Page<?> page) {
	PageAndHead widgets = getWidgets(page);
	if (widgets != null) {
	    ((HasWidgets) container).remove(widgets.pageWidget);
	    return true;
	}
	return false;
    }

    @Override
    public boolean remove(Page<?> page) {
	PageAndHead widgets = getWidgets(page);
	if (widgets != null) {
	    pages.remove(widgets);
	    ((HasWidgets) container).remove(widgets.pageWidget);
	    return true;
	}
	return false;
    }

    private PageAndHead getWidgets(Page<?> page) {
	return pages.get(page);
    }

    protected abstract void add(Widget container, Widget pageWidget, Widget headerWidget);

    protected abstract HeaderDisplay createHeaderDisplay(Page<?> page);

    protected abstract void focus(Widget container, Widget pageWidget);

}
