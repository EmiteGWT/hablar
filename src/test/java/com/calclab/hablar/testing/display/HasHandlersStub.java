package com.calclab.hablar.testing.display;

import com.google.gwt.event.shared.HandlerRegistration;

public class HasHandlersStub<T> {

    protected boolean isRemoved;
    protected T handler;
    protected HandlerRegistration registration;

    public HandlerRegistration addHandler(T handler) {
	this.isRemoved = false;
	this.handler = handler;
	this.registration = new HandlerRegistration() {
	    @Override
	    public void removeHandler() {
		isRemoved = true;
	    }
	};
	return registration;
    }

}
