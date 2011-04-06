package com.calclab.hablar.testing.display;

import com.google.gwt.event.dom.client.HasMouseOverHandlers;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

public class HasMouseOverHandlersStub implements HasMouseOverHandlers {

    private MouseOverHandler lastHandler;

    @Override
    public void fireEvent(GwtEvent<?> event) {
	lastHandler.onMouseOver((MouseOverEvent) event);
    }

    @Override
    public HandlerRegistration addMouseOverHandler(MouseOverHandler handler) {
	lastHandler = handler;
	return new HandlerRegistration() {
	    @Override
	    public void removeHandler() {
		lastHandler = null;
	    }
	};
    }

}
