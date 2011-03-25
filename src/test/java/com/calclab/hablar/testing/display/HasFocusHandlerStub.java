package com.calclab.hablar.testing.display;

import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.FocusHandler;
import com.google.gwt.event.dom.client.HasFocusHandlers;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

public class HasFocusHandlerStub implements HasFocusHandlers {

    private FocusHandler currentHandler;

    @Override
    public HandlerRegistration addFocusHandler(final FocusHandler handler) {
	currentHandler = handler;
	return new HandlerRegistration() {
	    @Override
	    public void removeHandler() {
		currentHandler = null;
	    }
	};
    }

    @Override
    public void fireEvent(final GwtEvent<?> event) {
	currentHandler.onFocus((FocusEvent) event);
    }

}
