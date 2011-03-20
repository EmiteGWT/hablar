package com.calclab.hablar.core.client.page.events;

import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.google.gwt.event.shared.GwtEvent;

public class VisibilityChangedEvent extends GwtEvent<VisibilityChangedHandler> {

    public static final Type<VisibilityChangedHandler> TYPE = new Type<VisibilityChangedHandler>();
    private final Page<?> page;
    private final Visibility visibility;

    public VisibilityChangedEvent(Page<?> page, Visibility visibility) {
	this.page = page;
	this.visibility = visibility;
    }

    @Override
    public Type<VisibilityChangedHandler> getAssociatedType() {
	return TYPE;
    }

    public Page<?> getPage() {
	return page;
    }

    public Visibility getVisibility() {
	return visibility;
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
