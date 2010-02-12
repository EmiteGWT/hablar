package com.calclab.hablar.groupchat.client;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.emite.im.client.chat.Chat.State;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.emite.xep.muc.client.RoomManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.rooms.client.ui.open.OpenRoomDisplay;
import com.calclab.hablar.rooms.client.ui.open.OpenRoomPresenter;
import com.calclab.hablar.rooms.client.ui.open.SelectRosterItemPresenter;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;

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
	final String reasonText = display.getMessage().getText();
	final RoomManager rooms = Suco.get(RoomManager.class);
	final Session session = Suco.get(Session.class);
	final String name = session.getCurrentUser().getNode();
	final XmppURI roomUri = XmppURI.uri(roomName, roomsService, name);
	rooms.open(roomUri);
	final Room room = (Room) rooms.getChat(roomUri);
	room.onStateChanged(new Listener<State>() {
	    @Override
	    public void onEvent(final State state) {
		if (state == Chat.State.ready) {
		    for (final SelectRosterItemPresenter item : getItems()) {
			if (item.isSelected()) {
			    room.sendInvitationTo(item.getItem().getJID(), reasonText);
			}
		    }
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
