package com.calclab.hablar.basic.client.ui.page.events;

import com.calclab.hablar.basic.client.ui.page.PageLogic;
import com.google.gwt.event.shared.GwtEvent;

public class OpenPageEvent extends GwtEvent<OpenPageHandler> {

    public static final Type<OpenPageHandler> TYPE = new Type<OpenPageHandler>();
    private final PageLogic page;

    public OpenPageEvent(PageLogic page) {
	this.page = page;
    }

    @Override
    public Type<OpenPageHandler> getAssociatedType() {
	return TYPE;
    }

    public PageLogic getPage() {
	return page;
    }

    @Override
    protected void dispatch(OpenPageHandler handler) {
	handler.onOpenPage(this);
    }

}
