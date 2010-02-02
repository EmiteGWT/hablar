package com.calclab.hablar.basic.client.ui.page.events;

import com.calclab.hablar.basic.client.ui.page.PageLogic;
import com.google.gwt.event.shared.GwtEvent;

public class UserMessageEvent extends GwtEvent<UserMessageHandler> {

    public static final Type<UserMessageHandler> TYPE = new Type<UserMessageHandler>();
    private final String message;
    private final PageLogic page;

    public UserMessageEvent(String status, PageLogic page) {
	this.message = status;
	this.page = page;
    }

    @Override
    public Type<UserMessageHandler> getAssociatedType() {
	return TYPE;
    }

    public String getMessage() {
	return message;
    }

    public PageLogic getPage() {
	return page;
    }

    @Override
    protected void dispatch(UserMessageHandler handler) {
	handler.onUserMessage(this);
    }

}
