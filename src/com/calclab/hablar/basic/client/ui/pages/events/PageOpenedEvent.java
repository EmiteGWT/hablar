package com.calclab.hablar.basic.client.ui.pages.events;

import com.calclab.hablar.basic.client.ui.page.PageView;
import com.google.gwt.event.shared.GwtEvent;

public class PageOpenedEvent extends GwtEvent<PageOpenedHandler> {
    public static final Type<PageOpenedHandler> TYPE = new Type<PageOpenedHandler>();

    private final PageView pageView;

    public PageOpenedEvent(PageView pageView) {
	this.pageView = pageView;
    }

    @Override
    public Type<PageOpenedHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(PageOpenedHandler handler) {
	handler.onPageOpened(pageView);
    }

}
