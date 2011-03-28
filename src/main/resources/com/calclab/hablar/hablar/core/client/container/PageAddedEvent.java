package com.calclab.hablar.core.client.container;

import com.calclab.hablar.core.client.page.Page;
import com.google.gwt.event.shared.GwtEvent;

public class PageAddedEvent extends GwtEvent<PageAddedHandler> {
    public static final Type<PageAddedHandler> TYPE = new Type<PageAddedHandler>();
    private final Page<?> page;
    private final PagesContainer container;

    public PageAddedEvent(final Page<?> page, final PagesContainer container) {
	this.page = page;
	this.container = container;
    }

    @Override
    public Type<PageAddedHandler> getAssociatedType() {
	return TYPE;
    }

    public PagesContainer getContainer() {
	return container;
    }

    public Page<?> getPage() {
	return page;
    }

    public boolean isType(final String type) {
	return page.getType().equals(type);
    }

    @Override
    protected void dispatch(final PageAddedHandler handler) {
	handler.onPageAdded(this);
    }

    @Override
    public String toDebugString() {
        return super.toDebugString() + getPage().getId();
    }
}
