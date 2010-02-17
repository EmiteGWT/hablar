package com.calclab.hablar.rooms.client;

import static com.calclab.hablar.rooms.client.HablarRooms.i18n;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.Chat.State;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.emite.xep.muc.client.RoomManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.rooms.client.open.OpenRoomDisplay;
import com.calclab.hablar.rooms.client.open.OpenRoomPresenter;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;

public class OpenNewRoomPresenter extends OpenRoomPresenter {

    private static final String TYPE = "OpenNewRoom";
    private int roomNumber = 1;
    private final String roomsService;

    public OpenNewRoomPresenter(final String roomsService, final HablarEventBus eventBus, final OpenRoomDisplay display) {
	super(TYPE, eventBus, display);
	this.roomsService = roomsService;
	display.setPageTitle(i18n().openNewRoom());
	display.setAcceptText(i18n().openNewRoomAction());
    }

    @Override
    protected void onAccept() {
	final RoomManager rooms = Suco.get(RoomManager.class);
	final Session session = Suco.get(Session.class);
	final XmppURI user = session.getCurrentUser();
	final String roomName = display.getRoomName().getText();
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
	roomNumber++;
    }

    @Override
    protected void onPageOpen() {
	final String roomName = i18n().roomId(roomNumber);
	display.getRoomName().setText(roomName);
	final Roster roster = Suco.get(Roster.class);
	setItems(roster.getItems(), true, false);
    }

}
