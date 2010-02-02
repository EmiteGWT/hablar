package com.calclab.hablar.basic.client;

import com.google.gwt.event.shared.HandlerManager;

public class DefaultEventBus extends HandlerManager implements HablarEventBus {

    public DefaultEventBus() {
	super(null);
    }

}
