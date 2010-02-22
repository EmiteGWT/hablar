package com.calclab.hablar.core.client.page.events;

import com.calclab.hablar.core.client.page.Page;
import com.google.gwt.event.shared.GwtEvent;

public class UserMessageEvent extends GwtEvent<UserMessageHandler> {
    public static final Type<UserMessageHandler> TYPE = new Type<UserMessageHandler>();
    private final String userMessage;
    private final String messageType;
    private final Page<?> page;

    public UserMessageEvent(final Page<?> page, final String message, final String messageType) {
	this.page = page;
	userMessage = message;
	this.messageType = messageType;
    }

    @Override
    public Type<UserMessageHandler> getAssociatedType() {
	return TYPE;
    }

    public String getMessageType() {
	return messageType;
    }

    public Page<?> getPage() {
	return page;
    }

    public String getUserMessage() {
	return userMessage;
    }

    @Override
    protected void dispatch(final UserMessageHandler handler) {
	handler.onUserMessage(this);
    }

}
