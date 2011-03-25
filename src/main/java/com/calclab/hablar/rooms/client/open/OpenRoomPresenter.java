package com.calclab.hablar.rooms.client.open;

import com.calclab.emite.core.client.events.StateChangedEvent;
import com.calclab.emite.core.client.events.StateChangedHandler;
import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.ChatStates;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.emite.xep.muc.client.RoomManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.groupchat.client.OpenGroupChatPresenter;
import com.calclab.hablar.rooms.client.OpenNewRoomPresenter;
import com.calclab.hablar.rooms.client.RoomName;

/**
 * An abstract open room presenter. You need to subclass this one.
 * 
 * @see OpenGroupChatPresenter
 * @see OpenNewRoomPresenter
 */
public abstract class OpenRoomPresenter extends EditRoomPresenter {
    protected final String roomsService;
    protected final XmppSession session;
    protected final RoomManager roomManager;

    public OpenRoomPresenter(final XmppSession session, final RoomManager roomManager, final String type,
	    final HablarEventBus eventBus, final EditRoomDisplay display, final String roomsService) {
	super(type, eventBus, display);
	this.session = session;
	this.roomManager = roomManager;
	this.roomsService = roomsService;

    }

    @Override
    protected void onAccept() {
	final XmppURI user = session.getCurrentUserURI();
	final String roomName = RoomName.encode(display.getRoomName().getValue(), user.getResource());
	final XmppURI roomUri = XmppURI.uri(roomName, roomsService, user.getNode());
	final Room room = (Room) roomManager.open(roomUri);

	room.addChatStateChangedHandler(true, new StateChangedHandler() {
	    @Override
	    public void onStateChanged(final StateChangedEvent event) {
		if (event.is(ChatStates.ready)) {
		    sendInvitations(room);
		}
	    }
	});
    }
}
