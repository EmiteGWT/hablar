package com.calclab.hablar.testing;

import com.calclab.hablar.core.client.mvp.DefaultEventBus;
import com.google.gwt.event.shared.GwtEvent;

public class EventBusTester extends DefaultEventBus {
    private GwtEvent<?> lastEvent;

    @Override
    public void fireEvent(GwtEvent<?> event) {
	this.lastEvent = event;
	super.fireEvent(event);
    }

    public GwtEvent<?> getLastEvent() {
	return lastEvent;
    }

    @SuppressWarnings("unchecked")
    public <T extends GwtEvent<?>> Class<T> getLastEventClass() {
	return (Class<T>) lastEvent.getClass();
    }
}
