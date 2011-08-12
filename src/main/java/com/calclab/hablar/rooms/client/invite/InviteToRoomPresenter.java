package com.calclab.hablar.rooms.client.invite;

import java.util.HashSet;
import java.util.Set;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.emite.xep.muc.client.Occupant;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.hablar.client.HablarMessages;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.rooms.client.RoomMessages;
import com.calclab.hablar.rooms.client.RoomName;
import com.calclab.hablar.rooms.client.open.EditRoomDisplay;
import com.calclab.hablar.rooms.client.open.EditRoomPresenter;

public class InviteToRoomPresenter extends EditRoomPresenter {
	public static final String TYPE = "InviteToRoom";

	private Room room;
	private final XmppRoster roster;

	public InviteToRoomPresenter(final XmppRoster roster, final HablarEventBus eventBus, final EditRoomDisplay display) {
		super(TYPE, eventBus, display);
		this.roster = roster;
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
		display.setPageTitle(RoomMessages.msg.invitePeopleToGroupChat());
		display.setAcceptText(HablarMessages.msg.acceptAction());

		final String roomName = RoomName.decode(room.getURI().getNode());
		display.getRoomName().setValue(roomName);
		display.setRoomNameEnabled(false);
		display.clearList();
		display.clearSelectionList();
		final Set<String> occupantUris = new HashSet<String>();
		for (final Occupant occupant : room.getOccupants()) {
			occupantUris.add(occupant.getOccupantUri().getResource());
		}
		for (final RosterItem item : roster.getItems()) {
			if (!occupantUris.contains(item.getJID().getNode())) {
				createItem(item, false);
			}
		}
	}

	public void setRoom(final Room room) {
		this.room = room;
	}
}
