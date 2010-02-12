package com.calclab.hablar.rooms.client.ui.invite;

import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;

public class InviteToRoomPage extends PagePresenter<InviteToRoomDisplay> {
    private static final String TYPE = "InviteToRoom";

    public InviteToRoomPage(final HablarEventBus eventBus, final InviteToRoomDisplay display) {
	super(TYPE, eventBus, display);
    }

}
