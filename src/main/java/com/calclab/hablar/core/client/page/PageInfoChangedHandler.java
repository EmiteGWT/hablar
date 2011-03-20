package com.calclab.hablar.core.client.page;

import com.google.gwt.event.shared.EventHandler;

public interface PageInfoChangedHandler extends EventHandler {

    void onPageInfoChanged(PageInfoChangedEvent event);

}
