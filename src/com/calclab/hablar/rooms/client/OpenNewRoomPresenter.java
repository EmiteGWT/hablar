package com.calclab.hablar.rooms.client;

import static com.calclab.hablar.rooms.client.HablarRooms.i18n;

import com.calclab.emite.im.client.roster.Roster;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.rooms.client.open.EditRoomDisplay;
import com.calclab.hablar.rooms.client.open.OpenRoomPresenter;
import com.calclab.suco.client.Suco;

public class OpenNewRoomPresenter extends OpenRoomPresenter {
    private static final String TYPE = "OpenNewRoom";
    private int roomNumber = 1;

    public OpenNewRoomPresenter(final String roomsService, final HablarEventBus eventBus, final EditRoomDisplay display) {
	super(TYPE, eventBus, display, roomsService);
	display.setPageTitle(i18n().openNewRoom());
	display.setAcceptText(i18n().openNewRoomAction());
    }

    @Override
    protected void onAccept() {
	super.onAccept();
	roomNumber++;
    }

    @Override
    protected void onPageOpen() {
	final String roomName = i18n().roomId(roomNumber);
	display.getRoomName().setText(roomName);
	final Roster roster = Suco.get(Roster.class);
	setItems(roster.getItems(), true, false);
	roomNameValidator.validate();
    }

}
