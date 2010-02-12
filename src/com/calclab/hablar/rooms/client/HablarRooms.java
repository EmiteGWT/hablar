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
import com.calclab.hablar.rooms.client.ui.invite.InviteToRoomPresenter;
import com.calclab.hablar.rooms.client.ui.open.OpenRoomWidget;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.google.gwt.core.client.EntryPoint;

public class HablarRooms implements EntryPoint {
    private static final String ACTION_ID_INVITE = "HablarRooms-inviteAction";
    private static final String ACTION_ID_OPENROOM = "HablarRooms-openRoom";

    public static void install(final Hablar hablar) {
	install(hablar, HablarRoomsConfig.getFromMeta());
    }

    public static void install(final Hablar hablar, final HablarRoomsConfig config) {
	new HablarRoomManager(hablar, config);

	final InviteToRoomPresenter invitePage = new InviteToRoomPresenter(hablar.getEventBus(), new OpenRoomWidget());
	hablar.addPage(invitePage, OverlayContainer.ROL);

	final OpenNewRoomPresenter openNewRoomPage = new OpenNewRoomPresenter(config.roomsService,
		hablar.getEventBus(), new OpenRoomWidget());

	hablar.addPageAddedHandler(new PageAddedHandler() {
	    @Override
	    public void onPageAdded(final PageAddedEvent event) {

		if (event.isType(RoomPage.TYPE)) {
		    final RoomPage roomPage = (RoomPage) event.getPage();
		    roomPage.addAction(createInviteAction(invitePage));
		} else if (event.isType(RosterPage.TYPE)) {
		    final RosterPage rosterPage = (RosterPage) event.getPage();
		    rosterPage.addAction(createOpenRoomAction(openNewRoomPage));
		}

	    }
	}, true);
    }

    protected static Action<RoomPage> createInviteAction(final InviteToRoomPresenter invitePage) {
	final String icon = HablarIcons.get(IconType.buddyAdd);
	return new SimpleAction<RoomPage>("Invite to this room", ACTION_ID_INVITE, icon) {
	    @Override
	    public void execute(final RoomPage target) {
		invitePage.setRoom(target.getRoom());
		invitePage.requestVisibility(Visibility.focused);
	    }
	};
    }

    protected static SimpleAction<RosterPage> createOpenRoomAction(final OpenNewRoomPresenter page) {
	final String name = "Open new room";
	final String icon = HablarIcons.get(IconType.buddyWait);
	final SimpleAction<RosterPage> action = new SimpleAction<RosterPage>(name, ACTION_ID_OPENROOM, icon) {
	    @Override
	    public void execute(final RosterPage target) {
		page.requestVisibility(Visibility.focused);
	    }
	};
	return action;
    }

    @Override
    public void onModuleLoad() {
    }

}
