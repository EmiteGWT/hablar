package com.calclab.hablar.testing.display;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

public class HasChangeHandlersStub extends HasHandlersStub<ChangeHandler> implements HasChangeHandlers {

    @Override
    public HandlerRegistration addChangeHandler(ChangeHandler handler) {
	return addHandler(handler);
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
	handler.onChange((ChangeEvent) event);
    }

}
