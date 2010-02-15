package com.calclab.hablar.groupchat.client;

import com.calclab.emite.im.client.roster.Roster;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.rooms.client.ui.open.OpenRoomPresenter;
import com.calclab.hablar.rooms.client.ui.open.OpenRoomWidget;
import com.calclab.suco.client.Suco;

public class ConvertToGroupChatPresenter extends OpenRoomPresenter {

    private static final String TYPE = "ConvertToGroupChat";

    public ConvertToGroupChatPresenter(final String roomsService, final HablarEventBus eventBus,
	    final OpenRoomWidget openRoomWidget) {
	super(TYPE, eventBus, openRoomWidget);
	display.setPageTitle("Convert this chat to group chat");
    }

    @Override
    protected void onAccept() {

    }

    @Override
    protected void onPageOpen() {
	final Roster roster = Suco.get(Roster.class);
	setItems(roster.getItems(), true);
    }

}
