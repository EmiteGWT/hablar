package com.calclab.hablar.rooms.client;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.rooms.client.ui.RoomPage;
import com.calclab.hablar.rooms.client.ui.invite.InviteToRoomPage;
import com.calclab.hablar.rooms.client.ui.invite.InviteToRoomWidget;
import com.google.gwt.core.client.EntryPoint;

public class HablarRooms implements EntryPoint {
    private static final String ACTION_ID_INVITE = "HablarRooms-inviteAction";

    protected static Action<RoomPage> createInviteAction(final InviteToRoomPage invitePage) {
	final String icon = HablarIcons.get(IconType.buddyAdd);
	return new SimpleAction<RoomPage>("Invite to this room", ACTION_ID_INVITE, icon) {
	    @Override
	    public void execute(final RoomPage target) {
		invitePage.requestVisibility(Visibility.focused);
	    }
	};
    }

    public static void install(final Hablar hablar) {
	install(hablar, HablarRoomsConfig.getFromMeta());
    }

    public static void install(final Hablar hablar, final HablarRoomsConfig config) {
	new HablarRoomManager(hablar, config);

	final InviteToRoomPage invitePage = new InviteToRoomPage(hablar.getEventBus(), new InviteToRoomWidget());
	hablar.addPage(invitePage, OverlayContainer.ROL);

	hablar.addPageAddedHandler(new PageAddedHandler() {
	    @Override
	    public void onPageAdded(final PageAddedEvent event) {
		final RoomPage roomPage = RoomPage.asRoom(event.getPage());
		if (roomPage != null) {
		    roomPage.addAction(createInviteAction(invitePage));
		}
	    }
	}, true);
    }

    @Override
    public void onModuleLoad() {
    }

}
