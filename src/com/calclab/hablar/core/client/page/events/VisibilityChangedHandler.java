package com.calclab.hablar.core.client.page.events;

import com.google.gwt.event.shared.EventHandler;

public interface VisibilityChangedHandler extends EventHandler {

    void onVisibilityChanged(VisibilityChangedEvent event);

}
