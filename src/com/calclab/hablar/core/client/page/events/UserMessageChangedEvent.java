package com.calclab.hablar.core.client.page.events;

import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PageState;
import com.google.gwt.event.shared.GwtEvent;

public class UserMessageChangedEvent extends GwtEvent<UserMessageChangedHandler> {
    public static final Type<UserMessageChangedHandler> TYPE = new Type<UserMessageChangedHandler>();
    private final Page<?> page;
    private final PageState pageState;

    public UserMessageChangedEvent(Page<?> page, PageState pageState) {
	this.page = page;
	this.pageState = pageState;
    }

    @Override
    public Type<UserMessageChangedHandler> getAssociatedType() {
	return TYPE;
    }

    public Page<?> getPage() {
	return page;
    }

    public PageState getPageState() {
	return pageState;
    }

    @Override
    protected void dispatch(UserMessageChangedHandler handler) {
	handler.onUserMessageChanged(this);
    }

}
