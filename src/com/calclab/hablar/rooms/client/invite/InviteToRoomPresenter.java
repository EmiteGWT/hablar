package com.calclab.hablar.rooms.client.invite;

import static com.calclab.hablar.rooms.client.HablarRooms.i18n;

import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.xep.muc.client.Occupant;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.rooms.client.RoomName;
import com.calclab.hablar.rooms.client.open.EditRoomDisplay;
import com.calclab.hablar.rooms.client.open.EditRoomPresenter;
import com.calclab.hablar.rooms.client.open.SelectRosterItemPresenter;
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
	for (final SelectRosterItemPresenter selectItem : getItems()) {
	    if (selectItem.isSelected()) {
		room.sendInvitationTo(selectItem.getItem().getJID(), reasonText);
	    }
	}
    }

    @Override
    protected void onPageOpen() {
	final Roster roster = Suco.get(Roster.class);
	display.setPageTitle(i18n().invitePeopleToRoom());
	final String roomName = RoomName.decode(room.getURI().toString());
	display.getRoomName().setText(roomName);
	display.setRoomNameEnabled(false);
	setItems(roster.getItems(), true, false);
	for (final Occupant occupant : room.getOccupants()) {
	    setItem(occupant.getURI(), false, true);
	}
    }
}
