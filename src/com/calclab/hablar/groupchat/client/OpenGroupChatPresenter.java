package com.calclab.hablar.groupchat.client;

import static com.calclab.hablar.rooms.client.HablarRooms.i18n;

import com.calclab.emite.im.client.roster.Roster;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.rooms.client.open.EditRoomDisplay;
import com.calclab.hablar.rooms.client.open.OpenRoomPresenter;
import com.calclab.suco.client.Suco;

public class OpenGroupChatPresenter extends OpenRoomPresenter {

    private static final String TYPE = "OpenGroupChat";
    private String groupName;

    public OpenGroupChatPresenter(final String roomsService, final HablarEventBus eventBus,
	    final EditRoomDisplay display) {
	super(TYPE, eventBus, display, roomsService);
    }

    public void setGroupName(final String groupName) {
	this.groupName = groupName;
    }

    @Override
    protected void onPageOpen() {
	display.getRoomName().setValue(groupName);
	display.setPageTitle(i18n().openNewGroupChat());
	display.setAcceptText(i18n().openNewGroupChatAction());

	final Roster roster = Suco.get(Roster.class);
	setItems(roster.getItemsByGroup(groupName), true);
	roomNameValidator.validate();
    }

}
