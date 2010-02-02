package com.calclab.hablar.basic.client.ui.pages.events;

import com.calclab.hablar.basic.client.ui.page.PageView;
import com.google.gwt.event.shared.EventHandler;

public interface PageClosedHandler extends EventHandler {

    void onPageClosed(PageView page);

}
