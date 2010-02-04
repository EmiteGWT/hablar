package com.calclab.hablar.core.client.page.events;

import com.calclab.hablar.core.client.page.PagePresenter;
import com.google.gwt.event.shared.GwtEvent;

public class FocusPageRequestEvent extends GwtEvent<FocusPageRequestHandler> {

    public static final Type<FocusPageRequestHandler> TYPE = new Type<FocusPageRequestHandler>();
    private final PagePresenter<?> pagePresenter;

    public FocusPageRequestEvent(PagePresenter<?> page) {
	this.pagePresenter = page;
    }

    @Override
    public Type<FocusPageRequestHandler> getAssociatedType() {
	return TYPE;
    }

    public PagePresenter<?> getPagePresenter() {
	return pagePresenter;
    }

    @Override
    protected void dispatch(FocusPageRequestHandler handler) {
	handler.onFocusPageRequest(this);
    }

}
