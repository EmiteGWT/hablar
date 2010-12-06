package com.calclab.hablar.core.client;

import java.util.List;

import com.calclab.hablar.core.client.container.ContainerAggregator;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.container.PagesContainer;
import com.calclab.hablar.core.client.container.main.MainContainer;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.container.overlay.OverlayPanel;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.pages.accordion.AccordionContainer;
import com.calclab.hablar.core.client.pages.accordion.AccordionLayout;
import com.calclab.hablar.core.client.pages.tabs.TabsContainer;
import com.calclab.hablar.core.client.pages.tabs.TabsLayout;
import com.calclab.hablar.core.client.pages.tabs.TabsLayout.TabHeaderSize;

public class HablarPresenter implements Hablar {
    public static HablarPresenter createAccordionPresenter(final HablarEventBus eventBus, final HablarDisplay display) {
	final MainContainer container = new AccordionContainer(eventBus, new AccordionLayout(display));
	return new HablarPresenter(eventBus, display, container);
    }

    public static HablarPresenter createTabsPresenter(final HablarEventBus eventBus, final HablarDisplay display,
	    final TabHeaderSize tabHeaderSize) {
	final MainContainer container = new TabsContainer(eventBus, new TabsLayout(display, tabHeaderSize));
	return new HablarPresenter(eventBus, display, container);
    }
    private final HablarDisplay display;

    private final HablarEventBus eventBus;

    private final ContainerAggregator aggregator;

    private HablarPresenter(final HablarEventBus eventBus, final HablarDisplay display, final MainContainer container) {
	this.eventBus = eventBus;
	this.display = display;
	this.aggregator = new ContainerAggregator(eventBus);

	final OverlayContainer overlayContainer = new OverlayContainer(eventBus, new OverlayPanel(display));
	addContainer(overlayContainer, Chain.after);
	addContainer(container, Chain.before);
    }

    @Override
    public void addContainer(final PagesContainer container, final Chain chain) {
	aggregator.addContainer(container, chain);
    }

    @Override
    public void addPage(final Page<?> page) {
	aggregator.add(page);
    }

    @Override
    public void addPage(final Page<?> page, final String containerType) {
	aggregator.addPage(page, containerType);
    }

    @Override
    public void addPageAddedHandler(final PageAddedHandler handler, final boolean fireAlreadyAdded) {
	aggregator.addPageAddedHandler(handler);
	if (fireAlreadyAdded) {
	    for (final Page<?> page : aggregator.getPages()) {
		handler.onPageAdded(new PageAddedEvent(page, null));
	    }
	}

    }

    @Override
    public PagesContainer getContainer(final String rol) {
	return aggregator.getContainer(rol);
    }

    @Override
    public HablarDisplay getDisplay() {
	return display;
    }

    @Override
    public HablarEventBus getEventBus() {
	return eventBus;
    }

    @Override
    public List<Page<?>> getPagesOfType(final String type) {
	return aggregator.getPagesOfType(type);
    }

}
