package com.calclab.hablar.rooms.client.open;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.Chat.State;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.emite.xep.muc.client.RoomManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.rooms.client.RoomName;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;

public abstract class OpenRoomPresenter extends EditRoomPresenter {
    private final String roomsService;

    public OpenRoomPresenter(final String type, final HablarEventBus eventBus, final EditRoomDisplay display,
	    final String roomsService) {
	super(type, eventBus, display);
	this.roomsService = roomsService;

    }

    @Override
    protected void onAccept() {
	final RoomManager rooms = Suco.get(RoomManager.class);
	final Session session = Suco.get(Session.class);
	final XmppURI user = session.getCurrentUser();
	final String roomName = RoomName.encode(display.getRoomName().getValue(), user.getResource());
	final XmppURI roomUri = XmppURI.uri(roomName, roomsService, user.getNode());
	final Room room = (Room) rooms.open(roomUri);
	room.onStateChanged(new Listener<State>() {
	    @Override
	    public void onEvent(final State state) {
		if (state == State.ready) {
		    sendInvitations(room);
		}
	    }
	});
    }

}
