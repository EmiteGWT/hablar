package com.calclab.hablar.core.client;

import java.util.ArrayList;
import java.util.List;

import com.calclab.hablar.core.client.HablarDisplay.Layout;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.events.VisibilityChangeRequestEvent;
import com.calclab.hablar.core.client.page.events.VisibilityChangeRequestHandler;
import com.calclab.hablar.core.client.pages.ContainerAggregator;
import com.calclab.hablar.core.client.pages.OverlayContainer;
import com.calclab.hablar.core.client.pages.PagesContainer;
import com.calclab.hablar.core.client.pages.accordion.AccordionContainer;
import com.calclab.hablar.core.client.pages.tabs.TabsContainer;
import com.google.gwt.core.client.GWT;

public class HablarPresenter implements Hablar {
    private final HablarDisplay display;
    private final HablarEventBus eventBus;
    private final ArrayList<PagePresenter<?>> pages;
    private Page<?> currentPage;
    private final ContainerAggregator aggregator;

    public HablarPresenter(HablarEventBus eventBus, Layout layout, HablarDisplay display) {
	this.eventBus = eventBus;
	this.display = display;
	this.pages = new ArrayList<PagePresenter<?>>();
	this.aggregator = new ContainerAggregator();

	aggregator.addContainer(new OverlayContainer(display.getContainer()));
	if (layout == HablarDisplay.Layout.accordion) {
	    aggregator.addContainer(new AccordionContainer(display.getContainer()));
	} else if (layout == HablarDisplay.Layout.tabs) {
	    aggregator.addContainer(new TabsContainer(display.getContainer()));
	}

	eventBus.addHandler(VisibilityChangeRequestEvent.TYPE, new VisibilityChangeRequestHandler() {
	    @Override
	    public void onVisibilityChangeRequest(VisibilityChangeRequestEvent event) {
		changeVisibility(event.getNewVisibility(), event.getPage());
	    }
	});

    }

    @Override
    public void addContainer(PagesContainer container) {
	aggregator.addContainer(container);
    }

    public void addPage(PagePresenter<?> page) {
	if (aggregator.add(page)) {
	    pages.add(page);
	}
    }

    public void addPage(PagePresenter<?> page, String containerType) {
	aggregator.addPage(page, containerType);
    }

    @Override
    public PagesContainer getContainer(String rol) {
	return aggregator.getContainer(rol);
    }

    @Override
    public HablarDisplay getDisplay() {
	return display;
    }

    public HablarEventBus getEventBus() {
	return eventBus;
    }

    public List<PagePresenter<?>> getPagePresentersOfType(String type) {
	ArrayList<PagePresenter<?>> list = new ArrayList<PagePresenter<?>>();
	for (PagePresenter<?> page : pages) {
	    if (type.equals(page.getType())) {
		list.add(page);
	    }
	}
	return list;
    }

    public void removePage(Page<?> page) {
	aggregator.remove(page);
    }

    private void changeVisibility(Visibility newVisibility, Page<?> page) {
	if (newVisibility == Visibility.focused) {
	    focusPage(page);
	} else if (newVisibility == Visibility.hidden) {
	    hidePage(page);
	} else if (newVisibility == Visibility.notFocused) {
	    unfocus(page);
	}
    }

    protected void focusPage(Page<?> page) {
	if (page.getVisibility() != Visibility.focused) {
	    if (aggregator.focus(page)) {
		if (currentPage != null) {
		    currentPage.setVisibility(Visibility.notFocused);
		}
		GWT.log("CURRENT PAGE: " + page.getId(), null);
		currentPage = page;
		page.setVisibility(Visibility.focused);
	    }
	}
    }

    protected void hidePage(Page<?> page) {
	if (page.getVisibility() != Visibility.hidden) {
	    if (aggregator.hide(page)) {
		page.setVisibility(Visibility.hidden);
	    }
	}
    }

    protected void unfocus(Page<?> page) {
	if (aggregator.unfocus(page)) {
	    page.setVisibility(Visibility.notFocused);
	}
    }
}
