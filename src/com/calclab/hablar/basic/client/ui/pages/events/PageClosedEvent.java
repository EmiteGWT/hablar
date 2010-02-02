package com.calclab.hablar.basic.client.ui.pages.events;

import com.calclab.hablar.basic.client.ui.page.PageView;
import com.google.gwt.event.shared.GwtEvent;

public class PageClosedEvent extends GwtEvent<PageClosedHandler> {

    public static final Type<PageClosedHandler> TYPE = new Type<PageClosedHandler>();
    private final PageView page;

    public PageClosedEvent(PageView page) {
	this.page = page;
    }

    @Override
    public Type<PageClosedHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(PageClosedHandler handler) {
	handler.onPageClosed(page);
    }

}
