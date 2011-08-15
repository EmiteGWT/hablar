package com.calclab.hablar.rooms.client;

import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.emite.xep.muc.client.RoomManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.rooms.client.open.EditRoomDisplay;
import com.calclab.hablar.rooms.client.open.OpenRoomPresenter;

public class OpenNewRoomPresenter extends OpenRoomPresenter {
	private static final String TYPE = "OpenNewRoom";
	private int roomNumber = 1;
	private final XmppRoster roster;

	public OpenNewRoomPresenter(final XmppSession session, final XmppRoster roster, final RoomManager roomManager, final String roomsService,
			final HablarEventBus eventBus, final EditRoomDisplay display) {
		super(session, roomManager, TYPE, eventBus, display, roomsService);
		this.roster = roster;
		display.setPageTitle(RoomMessages.msg.openNewGroupChat());
		display.setAcceptText(RoomMessages.msg.openNewGroupChatAction());
	}

	@Override
	protected void onAccept() {
		super.onAccept();
		roomNumber++;
	}

	@Override
	protected void onPageOpen() {
		final String roomName = RoomMessages.msg.groupChatId(roomNumber);
		display.getRoomName().setValue(roomName);
		setItems(roster.getItems(), false);
		roomNameValidator.validate();
	}

}
