package com.calclab.hablar.signals.client.unattended;

import com.calclab.hablar.core.client.page.Page;
import com.google.gwt.event.shared.GwtEvent;

public class UnattendedPagesChangedEvent extends GwtEvent<UnattendedMessageHandler> {
    public static enum ChangeType {
	added, removed
    }
    public static final Type<UnattendedMessageHandler> TYPE = new Type<UnattendedMessageHandler>();
    private final ChangeType changeType;
    private final Page<?> page;

    public UnattendedPagesChangedEvent(final ChangeType changeType, final Page<?> page) {
	this.changeType = changeType;
	this.page = page;
    }

    @Override
    public Type<UnattendedMessageHandler> getAssociatedType() {
	return TYPE;
    }

    public ChangeType getChangeType() {
	return changeType;
    }

    public Page<?> getPage() {
	return page;
    }

    @Override
    protected void dispatch(final UnattendedMessageHandler handler) {
	handler.handleUnattendedMessage(this);
    }

}
