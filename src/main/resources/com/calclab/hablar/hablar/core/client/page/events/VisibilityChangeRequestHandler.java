package com.calclab.hablar.core.client.page.events;

import com.google.gwt.event.shared.EventHandler;

public interface VisibilityChangeRequestHandler extends EventHandler {
    void onVisibilityChangeRequest(VisibilityChangeRequestEvent event);
}
