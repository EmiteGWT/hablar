package com.calclab.hablar.core.client;

import java.util.List;

import com.calclab.hablar.core.client.container.ContainerAggregator;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.container.PagesContainer;
import com.calclab.hablar.core.client.container.main.MainContainer;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.container.overlay.OverlayLayout;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.pages.accordion.AccordionContainer;
import com.calclab.hablar.core.client.pages.accordion.AccordionLayout;
import com.calclab.hablar.core.client.pages.tabs.TabsContainer;
import com.calclab.hablar.core.client.pages.tabs.TabsLayout;
import com.calclab.hablar.core.client.pages.tabs.TabsLayout.TabHeaderSize;

public class HablarPresenter implements Hablar {
    private final HablarDisplay display;
    private final HablarEventBus eventBus;
    private final ContainerAggregator aggregator;

    public static HablarPresenter createAccordionPresenter(HablarEventBus eventBus, HablarDisplay display) {
	MainContainer container = new AccordionContainer(eventBus, new AccordionLayout(display));
	return new HablarPresenter(eventBus, display, container);
    }

    public static HablarPresenter createTabsPresenter(HablarEventBus eventBus, HablarDisplay display,
	    TabHeaderSize tabHeaderSize) {
	MainContainer container = new TabsContainer(eventBus, new TabsLayout(display, tabHeaderSize));
	return new HablarPresenter(eventBus, display, container);
    }

    private HablarPresenter(HablarEventBus eventBus, HablarDisplay display, MainContainer container) {
	this.eventBus = eventBus;
	this.display = display;
	this.aggregator = new ContainerAggregator(eventBus);

	OverlayLayout overlayLayout = new OverlayLayout(display);
	OverlayContainer overlayContainer = new OverlayContainer(eventBus, overlayLayout);
	addContainer(overlayContainer, Chain.after);
	addContainer(container, Chain.before);
    }

    @Override
    public void addContainer(PagesContainer container, Chain chain) {
	aggregator.addContainer(container, chain);
    }

    public void addPage(Page<?> page) {
	aggregator.add(page);
    }

    public void addPage(Page<?> page, String containerType) {
	aggregator.addPage(page, containerType);
    }

    @Override
    public void addPageAddedHandler(PageAddedHandler handler, boolean fireAlreadyAdded) {
	aggregator.addPageAddedHandler(handler);
	if (fireAlreadyAdded) {
	    for (Page<?> page : aggregator.getPages()) {
		handler.onPageAdded(new PageAddedEvent(page, null));
	    }
	}

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

    public List<Page<?>> getPagesOfType(String type) {
	return aggregator.getPagesOfType(type);
    }
}
