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

	public PageAndHead(final Widget pageWidget, final Widget headWidget) {
	    this.pageWidget = pageWidget;
	    this.headWidget = headWidget;
	}
    }

    public static final String ROL = "Main";

    private final HashMap<Page<?>, PageAndHead> pages;

    private final MainLayout display;

    protected Page<?> focusedPage;

    public MainContainer(final HablarEventBus eventBus, final MainLayout layout) {
	display = layout;
	pages = new HashMap<Page<?>, PageAndHead>();
	eventBus.addHandler(VisibilityChangeRequestEvent.TYPE, new VisibilityChangeRequestHandler() {
	    @Override
	    public void onVisibilityChangeRequest(final VisibilityChangeRequestEvent event) {
		final Page<?> page = event.getPage();
		if (pages.containsKey(page)) {
		    changeVisibility(page, event.getNewVisibility());
		}
	    }
	});
	focusedPage = null;
    }

    @Override
    public boolean add(final Page<?> page) {
	final HeaderDisplay headerDisplay = display.createHeaderDisplay(page);
	final HeaderPresenter header = new HeaderPresenter(page, headerDisplay);
	final Widget pageWidget = page.getDisplay().asWidget();
	final Widget headWidget = header.getDisplay().asWidget();
	pages.put(page, new PageAndHead(pageWidget, headWidget));

	final Visibility initialVisibility = page.getVisibility();
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

    private PageAndHead getWidgets(final Page<?> page) {
	return pages.get(page);
    }

    protected void changeVisibility(final Page<?> page, final Visibility newVisibility) {
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

    protected void focus(final Page<?> page) {
	final PageAndHead widgets = getWidgets(page);
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

    protected boolean hide(final Page<?> page) {
	final PageAndHead widgets = getWidgets(page);
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

    protected void toggle(final Page<?> page) {

    }

    protected void unfocus(final Page<?> page) {
	if (focusedPage != null && focusedPage == page) {
	    page.setVisibility(Visibility.notFocused);
	    focusedPage = null;
	}
    }

}
