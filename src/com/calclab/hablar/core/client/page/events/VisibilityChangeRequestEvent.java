package com.calclab.hablar.core.client.page.events;

import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.google.gwt.event.shared.GwtEvent;

public class VisibilityChangeRequestEvent extends GwtEvent<VisibilityChangeRequestHandler> {

    public static final Type<VisibilityChangeRequestHandler> TYPE = new Type<VisibilityChangeRequestHandler>();
    private final Page<?> page;
    private final Visibility newVisibility;

    public VisibilityChangeRequestEvent(Page<?> page, Visibility newVisibility) {
	this.page = page;
	this.newVisibility = newVisibility;
    }

    @Override
    public Type<VisibilityChangeRequestHandler> getAssociatedType() {
	return TYPE;
    }

    public Visibility getNewVisibility() {
	return newVisibility;
    }

    public Page<?> getPage() {
	return page;
    }

    @Override
    public String toDebugString() {
	return super.toDebugString() + newVisibility + "(" + page.getId() + ")";
    }

    @Override
    protected void dispatch(VisibilityChangeRequestHandler handler) {
	handler.onVisibilityChangeRequest(this);
    }

}
