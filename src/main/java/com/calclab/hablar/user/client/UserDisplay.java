package com.calclab.hablar.user.client;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;

public interface UserDisplay extends Display {

    void addPage(EditorPage<?> page);

    HasClickHandlers getClose();
}
