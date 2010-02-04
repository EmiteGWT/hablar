package com.calclab.hablar.openchat.client.ui;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface OpenChatDisplay extends Display {
    HasClickHandlers getCancel();

    HasText getJabberId();

    HasClickHandlers getOpen();
}
