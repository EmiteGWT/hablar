package com.calclab.hablar.basic.client.ui.page.events;

import com.calclab.hablar.basic.client.ui.page.PageLogic;
import com.google.gwt.event.shared.EventHandler;

public interface ClosePageHandler extends EventHandler {

    void onPageClosed(PageLogic page);

}
