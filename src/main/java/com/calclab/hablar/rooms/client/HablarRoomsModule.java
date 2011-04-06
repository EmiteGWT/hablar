package com.calclab.hablar.rooms.client;

import com.calclab.hablar.rooms.client.existing.OpenExistingRoomWidget;
import com.calclab.hablar.rooms.client.open.EditRoomWidget;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarRoomsModule implements EntryPoint {

    @Override
    public void onModuleLoad() {
	final RoomsMessages messages = (RoomsMessages) GWT.create(RoomsMessages.class);
	HablarRooms.setMessages(messages);
	EditRoomWidget.setMessages(messages);
	OpenExistingRoomWidget.setMessages(messages);
    }

}
