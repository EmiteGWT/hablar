package com.calclab.hablar.openroom.client;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface OpenRoomDisplay extends Display {

    HasClickHandlers getCancel();

    HasClickHandlers getOpen();

    HasText getRoomName();
}
