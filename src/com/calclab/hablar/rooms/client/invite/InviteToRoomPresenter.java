package com.calclab.hablar.rooms.client.invite;

import static com.calclab.hablar.rooms.client.HablarRooms.i18n;

import java.util.HashSet;
import java.util.Set;

import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.xep.muc.client.Occupant;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.rooms.client.RoomName;
import com.calclab.hablar.rooms.client.open.EditRoomDisplay;
import com.calclab.hablar.rooms.client.open.EditRoomPresenter;
import com.calclab.suco.client.Suco;

public class InviteToRoomPresenter extends EditRoomPresenter {
    public static final String TYPE = "InviteToRoom";

    private Room room;

    public InviteToRoomPresenter(final HablarEventBus eventBus, final EditRoomDisplay display) {
	super(TYPE, eventBus, display);
    }

    public void setRoom(final Room room) {
	this.room = room;
    }

    @Override
    protected void onAccept() {
	final String reasonText = display.getMessage().getText();
	for (final RosterItem selectItem : getItems()) {
	    room.sendInvitationTo(selectItem.getJID(), reasonText);
	}
    }

    @Override
    protected void onPageOpen() {
	display.setPageTitle(i18n().invitePeopleToGroupChat());
	display.setAcceptText(i18n().acceptAction());

	final Roster roster = Suco.get(Roster.class);
	final String roomName = RoomName.decode(room.getURI().toString());
	display.getRoomName().setValue(roomName);
	display.setRoomNameEnabled(false);
	Set<String> occupantUris = new HashSet<String>();
	for (Occupant occupant : room.getOccupants()) {
	    occupantUris.add(occupant.getURI().getResource());
	}
	for (RosterItem item : roster.getItems()) {
	    if (!occupantUris.contains(item.getJID().getNode())) {
		createItem(item, false);
	    }
	}
    }
}
