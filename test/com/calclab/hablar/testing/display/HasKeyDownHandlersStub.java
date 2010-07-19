package com.calclab.hablar.testing.display;

import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

public class HasKeyDownHandlersStub implements HasKeyDownHandlers {

    private KeyDownHandler lastHandler;

    @Override
    public void fireEvent(GwtEvent<?> event) {
	lastHandler.onKeyDown((KeyDownEvent) event);
    }

    @Override
    public HandlerRegistration addKeyDownHandler(KeyDownHandler handler) {
	lastHandler = handler;
	return new HandlerRegistration() {
	    @Override
	    public void removeHandler() {
		lastHandler = null;
	    }
	};
    }

}
