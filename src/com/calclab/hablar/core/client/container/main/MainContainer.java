package com.calclab.hablar.core.client.container.main;

import java.util.HashMap;

import com.calclab.hablar.core.client.container.PagesContainer;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.events.VisibilityChangeRequestEvent;
import com.calclab.hablar.core.client.page.events.VisibilityChangeRequestHandler;
import com.calclab.hablar.core.client.pages.HeaderDisplay;
import com.calclab.hablar.core.client.pages.HeaderPresenter;
import com.google.gwt.user.client.ui.Widget;

public class MainContainer implements PagesContainer {
    private static class PageAndHead {
	public final Widget pageWidget;
	public final Widget headWidget;

	public PageAndHead(Widget pageWidget, Widget headWidget) {
	    this.pageWidget = pageWidget;
	    this.headWidget = headWidget;
	}
    }

    public static final String ROL = "Main";

    private final HashMap<Page<?>, PageAndHead> pages;

    private final MainLayout display;

    protected Page<?> focusedPage;

    public MainContainer(HablarEventBus eventBus, MainLayout layout) {
	this.display = layout;
	this.pages = new HashMap<Page<?>, PageAndHead>();
	eventBus.addHandler(VisibilityChangeRequestEvent.TYPE, new VisibilityChangeRequestHandler() {
	    @Override
	    public void onVisibilityChangeRequest(VisibilityChangeRequestEvent event) {
		Page<?> page = event.getPage();
		if (pages.containsKey(page)) {
		    changeVisibility(page, event.getNewVisibility());
		}
	    }
	});
	focusedPage = null;
    }

    @Override
    public boolean add(Page<?> page) {
	HeaderDisplay headerDisplay = display.createHeaderDisplay(page);
	HeaderPresenter header = new HeaderPresenter(page, headerDisplay);
	Widget pageWidget = page.getDisplay().asWidget();
	Widget headWidget = header.getDisplay().asWidget();
	pages.put(page, new PageAndHead(pageWidget, headWidget));

	Visibility initialVisibility = page.getVisibility();
	if (initialVisibility == Visibility.hidden) {
	} else if (initialVisibility == Visibility.focused) {
	    display.add(pageWidget, headWidget);
	    focus(page);
	} else if (initialVisibility == Visibility.notFocused) {
	    display.add(pageWidget, headWidget);
	    if (focusedPage == null) {
		// first page, can't be notFocused
		focus(page);
	    } else {
		focus(focusedPage);
	    }
	}
	return true;
    }

    @Override
    public String getRol() {
	return ROL;
    }

    @Override
    public Widget getWidget() {
	return display.getWidget();
    }

    protected boolean hide(Page<?> page) {
	PageAndHead widgets = getWidgets(page);
	if (widgets != null) {
	    display.remove(widgets.pageWidget);
	    page.setVisibility(Visibility.hidden);
	    if (page == focusedPage) {
		focusedPage = null;
	    }
	    return true;
	}
	return false;
    }

    private PageAndHead getWidgets(Page<?> page) {
	return pages.get(page);
    }

    protected void changeVisibility(Page<?> page, Visibility newVisibility) {
	if (newVisibility == Visibility.focused) {
	    focus(page);
	} else if (newVisibility == Visibility.notFocused) {
	    unfocus(page);
	} else if (newVisibility == Visibility.hidden) {
	    hide(page);
	} else if (newVisibility == Visibility.toggle) {
	    toggle(page);
	}
    }

    protected void focus(Page<?> page) {
	PageAndHead widgets = getWidgets(page);
	if (widgets != null) {
	    if (page != focusedPage) {
		unfocus(focusedPage);
		if (page.getVisibility() == Visibility.hidden) {
		    display.add(widgets.pageWidget, widgets.headWidget);
		}
		page.setVisibility(Visibility.focused);
		focusedPage = page;
	    }
	    display.focus(widgets.pageWidget);
	}
    }

    protected void toggle(Page<?> page) {

    }

    protected void unfocus(Page<?> page) {
	if (focusedPage != null && focusedPage == page) {
	    page.setVisibility(Visibility.notFocused);
	    focusedPage = null;
	}
    }

}
