package com.calclab.hablar.testing.display;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.user.client.ui.HasValue;

public class HasValueStub implements HasValue<Object> {

    private Object value;
    private ValueChangeHandler<Object> lastHandler;

    @Override
    public HandlerRegistration addValueChangeHandler(final ValueChangeHandler<Object> handler) {
	lastHandler = handler;
	return new HandlerRegistration() {
	    @Override
	    public void removeHandler() {
		lastHandler = null;
	    }
	};
    }

    @SuppressWarnings("unchecked")
    @Override
    public void fireEvent(final GwtEvent<?> event) {
	lastHandler.onValueChange((ValueChangeEvent<Object>) event);
    }

    @Override
    public Object getValue() {
	return value;
    }

    @Override
    public void setValue(final Object value) {
	setValue(value, true);
    }

    @Override
    public void setValue(final Object value, final boolean fireEvents) {
	this.value = value;
    }

}
