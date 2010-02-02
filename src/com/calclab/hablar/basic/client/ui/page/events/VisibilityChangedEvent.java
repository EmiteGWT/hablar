package com.calclab.hablar.basic.client.ui.page.events;

import com.calclab.hablar.basic.client.ui.page.PageLogic;
import com.google.gwt.event.shared.GwtEvent;

public class VisibilityChangedEvent extends GwtEvent<VisibilityChangedHandler> {
    public static final Type<VisibilityChangedHandler> TYPE = new Type<VisibilityChangedHandler>();
    private final PageLogic page;

    public VisibilityChangedEvent(PageLogic page) {
	this.page = page;
    }

    @Override
    public Type<VisibilityChangedHandler> getAssociatedType() {
	return TYPE;
    }

    public PageLogic getPage() {
	return page;
    }

    @Override
    protected void dispatch(VisibilityChangedHandler handler) {
	handler.onVisibilityChanged(this);
    }

}
