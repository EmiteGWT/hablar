package com.calclab.hablar.rooms.client.room;

import com.calclab.hablar.chat.client.ui.ChatWidget;
import com.calclab.hablar.rooms.client.occupant.OccupantsDisplay;
import com.calclab.hablar.rooms.client.occupant.OccupantsWidget;

public class RoomWidget extends ChatWidget implements RoomDisplay {

    public RoomWidget(final boolean sendButtonVisible) {
	super(sendButtonVisible);
    }

    @Override
    public OccupantsDisplay createOccupantsDisplay(final String roomId) {
	final OccupantsWidget occupantsWidget = new OccupantsWidget(roomId);
	actions.add(occupantsWidget);
	return occupantsWidget;
    }

}
