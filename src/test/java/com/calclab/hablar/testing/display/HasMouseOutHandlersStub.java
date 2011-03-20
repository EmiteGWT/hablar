package com.calclab.hablar.testing.display;

import com.google.gwt.event.dom.client.HasMouseOutHandlers;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

public class HasMouseOutHandlersStub implements HasMouseOutHandlers {

    private MouseOutHandler lastHandler;

    @Override
    public void fireEvent(GwtEvent<?> event) {
	lastHandler.onMouseOut((MouseOutEvent) event);
    }

    @Override
    public HandlerRegistration addMouseOutHandler(MouseOutHandler handler) {
	lastHandler = handler;
	return new HandlerRegistration() {
	    @Override
	    public void removeHandler() {
		lastHandler = null;
	    }
	};
    }

}
