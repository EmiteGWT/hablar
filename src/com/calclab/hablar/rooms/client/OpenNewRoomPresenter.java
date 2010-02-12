package com.calclab.hablar.rooms.client;

import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.rooms.client.ui.open.OpenRoomDisplay;
import com.calclab.hablar.rooms.client.ui.open.OpenRoomPresenter;

public class OpenNewRoomPresenter extends OpenRoomPresenter {

    private static final String TYPE = "OpenNewRoom";

    public OpenNewRoomPresenter(final String roomsService, final HablarEventBus eventBus, final OpenRoomDisplay display) {
	super(TYPE, eventBus, display);
    }

    @Override
    protected void onAccept() {

    }

    @Override
    protected void onPageOpen() {

    }

}
