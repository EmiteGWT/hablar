package com.calclab.hablar.testing.display;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;

public class HasClickHandlersStub extends HasHandlersStub<ClickHandler> implements HasClickHandlers {

    @Override
    public HandlerRegistration addClickHandler(ClickHandler handler) {
	return addHandler(handler);
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
	handler.onClick((ClickEvent) event);
    }

    public boolean isRemoved() {
	return isRemoved;
    }

}
