package com.calclab.hablar.rooms.client.notification;

import static com.calclab.hablar.rooms.client.HablarRooms.i18n;

import com.calclab.emite.xep.muc.client.Occupant;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.hablar.chat.client.ui.ChatDisplay;
import com.calclab.hablar.rooms.client.room.RoomDisplay;
import com.calclab.suco.client.events.Listener;

public class RoomNotificationPresenter {

    public RoomNotificationPresenter(final Room room, final RoomDisplay display) {
	room.onOccupantAdded(new Listener<Occupant>() {
	    @Override
	    public void onEvent(final Occupant occupant) {
		display.addMessage(null, i18n().hasJoined(occupant.getNick()), ChatDisplay.MessageType.info);
	    }
	});

	room.onOccupantRemoved(new Listener<Occupant>() {
	    @Override
	    public void onEvent(final Occupant occupant) {
		display.addMessage(null, i18n().hasLeft(occupant.getNick()), ChatDisplay.MessageType.info);
	    }
	});
    }

}
