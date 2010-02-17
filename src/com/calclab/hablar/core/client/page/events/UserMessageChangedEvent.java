package com.calclab.hablar.core.client.page.events;

import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PageState;
import com.google.gwt.event.shared.GwtEvent;

public class UserMessageChangedEvent extends GwtEvent<UserMessageChangedHandler> {
    public static final Type<UserMessageChangedHandler> TYPE = new Type<UserMessageChangedHandler>();
    private final Page<?> page;
    private final String userMessage;

    public UserMessageChangedEvent(final Page<?> page, final String userMessage) {
	this.page = page;
	this.userMessage = userMessage;
    }

    @Override
    public Type<UserMessageChangedHandler> getAssociatedType() {
	return TYPE;
    }

    public Page<?> getPage() {
	return page;
    }

    public PageState getPageState() {
	return page.getState();
    }

    public String getPageType() {
	return page.getType();
    }

    public String getUserMessage() {
	return userMessage;
    }

    @Override
    protected void dispatch(final UserMessageChangedHandler handler) {
	handler.onUserMessageChanged(this);
    }

}
