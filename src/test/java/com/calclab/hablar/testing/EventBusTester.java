package com.calclab.hablar.testing;

import java.util.ArrayList;

import com.calclab.hablar.core.client.mvp.DefaultEventBus;
import com.google.gwt.event.shared.GwtEvent;

public class EventBusTester extends DefaultEventBus {
    private GwtEvent<?> lastEvent;
    private final ArrayList<GwtEvent<?>> all;

    public EventBusTester() {
	all = new ArrayList<GwtEvent<?>>();
    }

    @Override
    public void fireEvent(GwtEvent<?> event) {
	all.add(event);
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

    public boolean receivedEventOfClass(Class<? extends GwtEvent<?>> eventClass) {
	for (GwtEvent<?> event : all) {
	    if (eventClass.equals(event.getClass())) {
		return true;
	    }
	}
	return false;
    }
}
