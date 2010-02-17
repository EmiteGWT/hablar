package com.calclab.hablar.signals.client.unattended;

import com.google.gwt.event.shared.GwtEvent;

public class UnattendedChatsChangedEvent extends GwtEvent<UnattendedMessageHandler> {
    public static final Type<UnattendedMessageHandler> TYPE = new Type<UnattendedMessageHandler>();
    private final UnattendedChatPages unattendedChatPages;

    public UnattendedChatsChangedEvent(UnattendedChatPages unattendedChatPages) {
	this.unattendedChatPages = unattendedChatPages;
    }

    @Override
    public Type<UnattendedMessageHandler> getAssociatedType() {
	return TYPE;
    }

    public UnattendedChatPages getUnattendedChatPages() {
	return unattendedChatPages;
    }

    @Override
    protected void dispatch(UnattendedMessageHandler handler) {
	handler.handleUnattendedMessage(this);
    }

}
