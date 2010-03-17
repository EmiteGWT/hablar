package com.calclab.hablar.signals.client.unattended;

import com.calclab.hablar.core.client.page.Page;
import com.google.gwt.event.shared.GwtEvent;

public class UnattendedChatsChangedEvent extends GwtEvent<UnattendedChatsChangedHandler> {
    public static enum ChangeType {
	added, removed
    }
    public static final Type<UnattendedChatsChangedHandler> TYPE = new Type<UnattendedChatsChangedHandler>();
    private final ChangeType changeType;
    private final Page<?> page;

    public UnattendedChatsChangedEvent(final ChangeType changeType, final Page<?> page) {
	this.changeType = changeType;
	this.page = page;
    }

    @Override
    public Type<UnattendedChatsChangedHandler> getAssociatedType() {
	return TYPE;
    }

    public ChangeType getChangeType() {
	return changeType;
    }

    public Page<?> getPage() {
	return page;
    }

    @Override
    protected void dispatch(final UnattendedChatsChangedHandler handler) {
	handler.handleUnattendedChatChange(this);
    }

}
