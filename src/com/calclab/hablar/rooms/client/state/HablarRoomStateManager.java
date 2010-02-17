package com.calclab.hablar.rooms.client.state;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.xep.chatstate.client.ChatStateManager.ChatState;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.emite.xep.mucchatstate.client.MUCChatStateManager;
import com.calclab.emite.xep.mucchatstate.client.RoomChatStateManager;
import com.calclab.hablar.chat.client.state.HablarChatStateManager;
import com.calclab.hablar.rooms.client.room.RoomPresenter;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener2;
import com.google.gwt.user.client.ui.HasText;

public class HablarRoomStateManager {

    public HablarRoomStateManager(final RoomPresenter roomPage) {
	final Room room = roomPage.getRoom();
	final MUCChatStateManager manager = Suco.get(MUCChatStateManager.class);
	final RoomChatStateManager occupantsStateManager = manager.getRoomOccupantsChatStateManager(room);

	occupantsStateManager.onChatStateChanged(new Listener2<XmppURI, ChatState>() {
	    @Override
	    public void onEvent(final XmppURI uri, final ChatState state) {
		final HasText stateDisplay = roomPage.getDisplay().getState();
		stateDisplay.setText(HablarChatStateManager.getStateText(state, uri.getNode()));
	    }
	});
    }

}
