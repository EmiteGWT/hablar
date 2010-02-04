package com.calclab.hablar.core.client.page.events;

import com.calclab.hablar.core.client.page.Page;
import com.google.gwt.event.shared.GwtEvent;

public class HidePageRequestEvent extends GwtEvent<HidePageRequestHandler> {

    public static final Type<HidePageRequestHandler> TYPE = new Type<HidePageRequestHandler>();
    private final Page<?> page;

    public HidePageRequestEvent(Page<?> page) {
	this.page = page;
    }

    @Override
    public Type<HidePageRequestHandler> getAssociatedType() {
	return TYPE;
    }

    public Page<?> getPage() {
	return page;
    }

    @Override
    protected void dispatch(HidePageRequestHandler handler) {
	handler.onHidePageRequest(this);
    }

}
