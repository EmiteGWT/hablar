package com.calclab.hablar.core.client.ui.prompt;

import com.google.gwt.event.shared.GwtEvent;

public class ConfirmEvent extends GwtEvent<ConfirmHandler> {

    public static final Type<ConfirmHandler> TYPE = new Type<ConfirmHandler>();
    public final String message;
    public final UserConfirmationHandler handler;
    public final String title;

    public ConfirmEvent(String title, String message, UserConfirmationHandler handler) {
	this.title = title;
	this.message = message;
	this.handler = handler;
    }

    @Override
    public Type<ConfirmHandler> getAssociatedType() {
	return TYPE;
    }

    @Override
    protected void dispatch(ConfirmHandler handler) {
	handler.onConfirm(this);
    }

}
