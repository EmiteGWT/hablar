package com.calclab.hablar.rooms.client.open;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.validators.HasState;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface EditRoomDisplay extends Display {

    void addItem(SelectRosterItemDisplay itemDisplay);

    void clearList();

    SelectRosterItemDisplay createItem();

    HasState<Boolean> getAcceptEnabled();

    HasClickHandlers getCancel();

    HasClickHandlers getInvite();

    HasText getMessage();

    HasText getRoomName();

    HasText getRoomNameError();

    HasKeyPressHandlers getRoomNameKeys();

    void setAcceptText(String string);

    void setPageTitle(String text);

    void setRoomNameEnabled(boolean enabled);

}
