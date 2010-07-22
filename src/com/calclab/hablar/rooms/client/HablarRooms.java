package com.calclab.hablar.rooms.client;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.icon.Icons;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.rooms.client.existing.OpenExistingRoomPresenter;
import com.calclab.hablar.rooms.client.existing.OpenExistingRoomWidget;
import com.calclab.hablar.rooms.client.invite.InviteToRoomPresenter;
import com.calclab.hablar.rooms.client.open.EditRoomWidget;
import com.calclab.hablar.rooms.client.room.RoomPage;
import com.calclab.hablar.rooms.client.room.RoomPresenter;
import com.calclab.hablar.rooms.client.state.HablarRoomStateManager;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarRooms implements EntryPoint {
    private static final String ACTION_ID_INVITE = "HablarRooms-inviteAction";
    private static final String ACTION_ID_OPENROOM = "HablarRooms-openRoom";
    private static final String ACTION_ID_OPENEXISTINGROOM = "HablarRooms-openExistingRoom";
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

	final OpenExistingRoomPresenter openExistingRoomPresenter = new OpenExistingRoomPresenter(config.roomsService,
		hablar.getEventBus(), new OpenExistingRoomWidget());
	hablar.addPage(openExistingRoomPresenter, OverlayContainer.ROL);

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
		    rosterPage.addAction(createOpenExistingRoomAction(openExistingRoomPresenter));
		}

	    }
	}, true);

    }

    public static void setMessages(final RoomsMessages messages) {
	roomMessages = messages;
    }

    protected static Action<RoomPage> createInviteAction(final InviteToRoomPresenter invitePage) {
	final String buddyAddIcon = Icons.BUDDY_ADD;
	return new SimpleAction<RoomPage>(i18n().inviteToThisGroupChat(), ACTION_ID_INVITE, buddyAddIcon) {
	    @Override
	    public void execute(final RoomPage target) {
		invitePage.setRoom(target.getRoom());
		invitePage.requestVisibility(Visibility.focused);
	    }
	};
    }

    protected static SimpleAction<RosterPage> createOpenRoomAction(final OpenNewRoomPresenter page) {
	final String name = i18n().openNewGroupChatTooltip();
	final String icon = Icons.GROUP_CHAT_ADD;
	final SimpleAction<RosterPage> action = new SimpleAction<RosterPage>(name, ACTION_ID_OPENROOM, icon) {
	    @Override
	    public void execute(final RosterPage target) {
		page.requestVisibility(Visibility.focused);
	    }
	};
	return action;
    }

    protected static SimpleAction<RosterPage> createOpenExistingRoomAction(final OpenExistingRoomPresenter page) {
	final String name = i18n().openExistingRoom();
	final String icon = Icons.GROUP_CHAT;
	final SimpleAction<RosterPage> action = new SimpleAction<RosterPage>(name, ACTION_ID_OPENEXISTINGROOM, icon) {
	    @Override
	    public void execute(final RosterPage target) {
		page.requestVisibility(Visibility.focused);
	    }
	};
	return action;
    }

    @Override
    public void onModuleLoad() {
	final RoomsMessages messages = (RoomsMessages) GWT.create(RoomsMessages.class);
	HablarRooms.setMessages(messages);
	EditRoomWidget.setMessages(messages);
	OpenExistingRoomWidget.setMessages(messages);
    }

}
