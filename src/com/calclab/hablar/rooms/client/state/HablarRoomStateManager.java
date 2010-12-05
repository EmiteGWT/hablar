package com.calclab.hablar.rooms.client.state;

import com.calclab.emite.xep.muc.client.Room;
import com.calclab.emite.xep.mucchatstate.client.MUCChatStateManager;
import com.calclab.emite.xep.mucchatstate.client.RoomChatStateManager;
import com.calclab.emite.xep.mucchatstate.client.events.RoomChatStateNotificationEvent;
import com.calclab.emite.xep.mucchatstate.client.events.RoomChatStateNotificationHandler;
import com.calclab.hablar.chat.client.state.HablarChatStateManager;
import com.calclab.hablar.rooms.client.room.RoomPresenter;
import com.google.gwt.user.client.ui.HasText;

/**
 * Sets the state of the room
 */
public class HablarRoomStateManager {

    public HablarRoomStateManager(final MUCChatStateManager manager, final RoomPresenter roomPage) {
	final Room room = roomPage.getRoom();
	final RoomChatStateManager occupantsStateManager = manager.getRoomOccupantsChatStateManager(room);

	occupantsStateManager.addRoomChatStateNotificationHandler(new RoomChatStateNotificationHandler() {
	    @Override
	    public void onRoomChatStateNotification(final RoomChatStateNotificationEvent event) {
		final HasText stateDisplay = roomPage.getDisplay().getState();
		stateDisplay.setText(HablarChatStateManager.getStateText(event.getChatState(), event.getFrom()
			.getResource()));
	    }
	});

    }

}
