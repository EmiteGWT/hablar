package com.calclab.hablar.core.client.page.events;

import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PageState;
import com.google.gwt.event.shared.GwtEvent;

public class VisibilityChangedEvent extends GwtEvent<VisibilityChangedHandler> {

    public static final Type<VisibilityChangedHandler> TYPE = new Type<VisibilityChangedHandler>();
    private final Page<?> page;
    private final PageState pageState;

    public VisibilityChangedEvent(Page<?> page, PageState pageState) {
	this.page = page;
	this.pageState = pageState;
    }

    @Override
    public Type<VisibilityChangedHandler> getAssociatedType() {
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
	return super.toDebugString() + page.getVisibility() + "(" + page.getId() + ")";
    }

    @Override
    protected void dispatch(VisibilityChangedHandler handler) {
	handler.onVisibilityChanged(this);
    }

}
