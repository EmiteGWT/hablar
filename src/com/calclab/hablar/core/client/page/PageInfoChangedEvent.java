package com.calclab.hablar.core.client.page;

import com.google.gwt.event.shared.GwtEvent;

public class PageInfoChangedEvent extends GwtEvent<PageInfoChangedHandler> {

    public static final Type<PageInfoChangedHandler> TYPE = new Type<PageInfoChangedHandler>();
    private final Page<?> page;
    private final PageState pageState;

    public PageInfoChangedEvent(final Page<?> page, final PageState pageState) {
	this.page = page;
	this.pageState = pageState;
    }

    @Override
    public Type<PageInfoChangedHandler> getAssociatedType() {
	return TYPE;
    }

    public Page<?> getPage() {
	return page;
    }

    public PageState getPageState() {
	return pageState;
    }

    @Override
    public String toDebugString() {
	return super.toDebugString() + pageState + " (" + page + ")";
    }

    @Override
    protected void dispatch(final PageInfoChangedHandler handler) {
	handler.onPageInfoChanged(this);
    }

}
