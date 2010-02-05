package com.calclab.hablar.openchat.client.ui;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;

public interface OpenChatDisplay extends Display {
    HasValue<Boolean> getAddToRoster();

    HasClickHandlers getCancel();

    HasText getJabberId();

    HasClickHandlers getOpen();
}
