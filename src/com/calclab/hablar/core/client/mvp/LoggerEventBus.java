package com.calclab.hablar.core.client.mvp;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.GwtEvent;

public class LoggerEventBus extends DefaultEventBus {

    @Override
    public void fireEvent(GwtEvent<?> event) {
	GWT.log("EVENT: " + event.toDebugString(), null);
	super.fireEvent(event);
    }

}
