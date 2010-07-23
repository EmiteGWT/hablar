package com.calclab.hablar.rooms.client.existing;

import java.util.List;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;

public interface OpenExistingRoomDisplay extends Display {

    void setRooms(List<ExistingRoom> rooms);

    XmppURI getSelectedRoom();

    HasClickHandlers getAccept();

    HasClickHandlers getCancel();

}