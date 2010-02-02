package com.calclab.hablar.basic.client.ui.page.events;

import com.calclab.hablar.basic.client.ui.page.PageLogic;
import com.google.gwt.event.shared.GwtEvent;

public class ClosePageEvent extends GwtEvent<ClosePageHandler> {

    public static final Type<ClosePageHandler> TYPE = new Type<ClosePageHandler>();
    private final PageLogic page;

    public ClosePageEvent(PageLogic page) {
	this.page = page;
    }

    @Override
    public Type<ClosePageHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(ClosePageHandler handler) {
	handler.onPageClosed(page);
    }

}
