package com.calclab.hablar.groupchat.client;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.emite.im.client.chat.Chat.State;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.emite.xep.muc.client.RoomManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.rooms.client.open.OpenRoomDisplay;
import com.calclab.hablar.rooms.client.open.OpenRoomPresenter;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;

public class OpenGroupChatPresenter extends OpenRoomPresenter {

    private static final String TYPE = "OpenGroupChat";
    private String groupName;
    private final String roomsService;

    public OpenGroupChatPresenter(final String roomsService, final HablarEventBus eventBus,
	    final OpenRoomDisplay display) {
	super(TYPE, eventBus, display);
	this.roomsService = roomsService;
    }

    public void setGroupName(final String groupName) {
	this.groupName = groupName;
    }

    @Override
    protected void onAccept() {
	final String roomName = display.getRoomName().getText();
	final RoomManager rooms = Suco.get(RoomManager.class);
	final Session session = Suco.get(Session.class);
	final String name = session.getCurrentUser().getNode();
	final XmppURI roomUri = XmppURI.uri(roomName, roomsService, name);
	final Room room = (Room) rooms.open(roomUri);
	room.onStateChanged(new Listener<State>() {
	    @Override
	    public void onEvent(final State state) {
		GWT.log("ROOM CHANGE");
		if (state == Chat.State.ready) {
		    GWT.log("ROOM READY");
		    sendInvitations(room);
		}
	    }
	});
    }

    @Override
    protected void onPageOpen() {
	display.getRoomName().setText(groupName);
	display.setPageTitle("Open Group Chat");
	display.setAcceptText("Open Group Chat");

	final Roster roster = Suco.get(Roster.class);
	setItems(roster.getItemsByGroup(groupName), false);
    }

}
