package com.calclab.hablar.testing.display;

import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

public class HasKeyPressHandlersStub implements HasKeyPressHandlers {

    private KeyPressHandler lastHandler;

    @Override
    public HandlerRegistration addKeyPressHandler(final KeyPressHandler handler) {
	lastHandler = handler;
	return new HandlerRegistration() {
	    @Override
	    public void removeHandler() {
		lastHandler = null;
	    }
	};
    }

    @Override
    public void fireEvent(final GwtEvent<?> event) {
	lastHandler.onKeyPress((KeyPressEvent) event);
    }

}
