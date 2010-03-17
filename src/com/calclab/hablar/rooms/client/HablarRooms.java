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
import com.calclab.hablar.rooms.client.invite.InviteToRoomPresenter;
import com.calclab.hablar.rooms.client.open.EditRoomWidget;
import com.calclab.hablar.rooms.client.room.RoomPresenter;
import com.calclab.hablar.rooms.client.state.HablarRoomStateManager;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarRooms implements EntryPoint {
    private static final String ACTION_ID_INVITE = "HablarRooms-inviteAction";
    private static final String ACTION_ID_OPENROOM = "HablarRooms-openRoom";
    private static RoomsMessages roomMessages;

    public static RoomsMessages i18n() {
	return roomMessages;
    }

    public static void install(final Hablar hablar) {
	install(hablar, HablarRoomsConfig.getFromMeta());
    }

    public static void install(final Hablar hablar, final HablarRoomsConfig config) {
	new HablarRoomManager(hablar, config);

	final InviteToRoomPresenter invitePage = new InviteToRoomPresenter(hablar.getEventBus(), new EditRoomWidget());
	hablar.addPage(invitePage, OverlayContainer.ROL);

	final OpenNewRoomPresenter openNewRoomPage = new OpenNewRoomPresenter(config.roomsService,
		hablar.getEventBus(), new EditRoomWidget());
	hablar.addPage(openNewRoomPage, OverlayContainer.ROL);

	hablar.addPageAddedHandler(new PageAddedHandler() {
	    @Override
	    public void onPageAdded(final PageAddedEvent event) {

		if (event.isType(RoomPresenter.TYPE)) {
		    final RoomPresenter roomPage = (RoomPresenter) event.getPage();
		    roomPage.addAction(createInviteAction(invitePage));
		    new HablarRoomStateManager(roomPage);
		} else if (event.isType(RosterPage.TYPE)) {
		    final RosterPage rosterPage = (RosterPage) event.getPage();
		    rosterPage.addAction(createOpenRoomAction(openNewRoomPage));
		}

	    }
	}, true);

    }

    public static void setMessages(final RoomsMessages messages) {
	roomMessages = messages;
    }

    protected static Action<RoomPresenter> createInviteAction(final InviteToRoomPresenter invitePage) {
	final String icon = HablarIcons.get(IconType.buddyAdd);
	return new SimpleAction<RoomPresenter>(i18n().inviteToThisGroupChat(), ACTION_ID_INVITE, icon) {
	    @Override
	    public void execute(final RoomPresenter target) {
		invitePage.setRoom(target.getRoom());
		invitePage.requestVisibility(Visibility.focused);
	    }
	};
    }

    protected static SimpleAction<RosterPage> createOpenRoomAction(final OpenNewRoomPresenter page) {
	final String name = "Open new group chat";
	final String icon = HablarIcons.get(IconType.groupChat);
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
	HablarRooms.setMessages((RoomsMessages) GWT.create(RoomsMessages.class));
    }

}
