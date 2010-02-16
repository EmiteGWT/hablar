package com.calclab.hablar.rooms.client.open;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface OpenRoomDisplay extends Display {

    void addItem(SelectRosterItemDisplay itemDisplay);

    void clearList();

    SelectRosterItemDisplay createItem();

    HasClickHandlers getCancel();

    HasClickHandlers getInvite();

    HasText getMessage();

    HasText getRoomName();

    void setAcceptText(String string);

    void setPageTitle(String text);

    void setRoomNameEnabled(boolean enabled);

}
