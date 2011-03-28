package com.calclab.hablar.core.client.page.events;

import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.google.gwt.event.shared.GwtEvent;

public class VisibilityChangeRequestEvent extends GwtEvent<VisibilityChangeRequestHandler> {

    private static final Type<VisibilityChangeRequestHandler> TYPE = new Type<VisibilityChangeRequestHandler>();

    public static void bind(final HablarEventBus eventBus, final VisibilityChangeRequestHandler handler) {
	eventBus.addHandler(TYPE, handler);
    }
    private final Page<?> page;

    private final Visibility newVisibility;

    public VisibilityChangeRequestEvent(final Page<?> page, final Visibility newVisibility) {
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
	return super.toDebugString() + newVisibility + "(" + page.getId() + ") - current: " + page.getVisibility();
    }

    @Override
    protected void dispatch(final VisibilityChangeRequestHandler handler) {
	handler.onVisibilityChangeRequest(this);
    }

}
