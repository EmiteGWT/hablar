package com.calclab.hablar.rooms.client;

import static com.calclab.hablar.rooms.client.HablarRooms.i18n;

import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.emite.xep.muc.client.RoomManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.rooms.client.open.EditRoomDisplay;
import com.calclab.hablar.rooms.client.open.OpenRoomPresenter;
import com.calclab.suco.client.Suco;

public class OpenNewRoomPresenter extends OpenRoomPresenter {
    private static final String TYPE = "OpenNewRoom";
    private int roomNumber = 1;

    public OpenNewRoomPresenter(final String roomsService, final HablarEventBus eventBus, final EditRoomDisplay display) {
	super(Suco.get(XmppSession.class), Suco.get(RoomManager.class), TYPE, eventBus, display, roomsService);
	display.setPageTitle(i18n().openNewGroupChat());
	display.setAcceptText(i18n().openNewGroupChatAction());
    }

    @Override
    protected void onAccept() {
	super.onAccept();
	roomNumber++;
    }

    @Override
    protected void onPageOpen() {
	final String roomName = i18n().groupChatId(roomNumber);
	display.getRoomName().setValue(roomName);
	final XmppRoster roster = Suco.get(XmppRoster.class);
	setItems(roster.getItems(), false);
	roomNameValidator.validate();
    }

}
