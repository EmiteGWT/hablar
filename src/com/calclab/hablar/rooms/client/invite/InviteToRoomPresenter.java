package com.calclab.hablar.rooms.client.invite;

import static com.calclab.hablar.rooms.client.HablarRooms.i18n;

import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.xep.muc.client.Occupant;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.rooms.client.open.OpenRoomDisplay;
import com.calclab.hablar.rooms.client.open.OpenRoomPresenter;
import com.calclab.hablar.rooms.client.open.SelectRosterItemPresenter;
import com.calclab.suco.client.Suco;

public class InviteToRoomPresenter extends OpenRoomPresenter {
    public static final String TYPE = "InviteToRoom";

    private Room room;

    public InviteToRoomPresenter(final HablarEventBus eventBus, final OpenRoomDisplay display) {
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
	display.getRoomName().setText(room.getURI().toString());
	display.setRoomNameEnabled(false);
	setItems(roster.getItems(), true);
	for (final Occupant occupant : room.getOccupants()) {
	    setItem(occupant.getURI(), false, true);
	}
    }
}
