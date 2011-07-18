package com.calclab.hablar.rooms.client;

import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.emite.xep.muc.client.RoomManager;
import com.calclab.emite.xep.mucchatstate.client.MUCChatStateManager;
import com.calclab.emite.xep.mucdisco.client.RoomDiscoveryManager;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.icons.client.IconsBundle;
import com.calclab.hablar.rooms.client.existing.OpenExistingRoomPresenter;
import com.calclab.hablar.rooms.client.existing.OpenExistingRoomWidget;
import com.calclab.hablar.rooms.client.invite.InviteToRoomPresenter;
import com.calclab.hablar.rooms.client.open.EditRoomWidget;
import com.calclab.hablar.rooms.client.room.RoomPage;
import com.calclab.hablar.rooms.client.room.RoomPresenter;
import com.calclab.hablar.rooms.client.state.HablarRoomStateManager;
import com.calclab.hablar.roster.client.page.RosterPage;
import com.google.inject.Inject;

public class HablarRooms {
	private static final String ACTION_ID_INVITE = "HablarRooms-inviteAction";
	private static final String ACTION_ID_OPENROOM = "HablarRooms-openRoom";
	private static final String ACTION_ID_OPENEXISTINGROOM = "HablarRooms-openExistingRoom";

	@Inject
	public HablarRooms(final Hablar hablar, final HablarRoomsConfig config, final XmppSession session, final XmppRoster roster, final RoomManager roomManager,
			final RoomDiscoveryManager roomDiscoveryManager, final MUCChatStateManager mucChatStateManager) {
		new HablarRoomManager(session, roster, roomManager, hablar, config);

		final InviteToRoomPresenter invitePage = new InviteToRoomPresenter(roster, hablar.getEventBus(), new EditRoomWidget());
		hablar.addPage(invitePage, OverlayContainer.ROL);

		final OpenNewRoomPresenter openNewRoomPage = new OpenNewRoomPresenter(session, roster, roomManager, config.roomsService, hablar.getEventBus(),
				new EditRoomWidget());
		hablar.addPage(openNewRoomPage, OverlayContainer.ROL);

		final OpenExistingRoomPresenter openExistingRoomPresenter = new OpenExistingRoomPresenter(session, roomManager, roomDiscoveryManager,
				config.roomsService, hablar.getEventBus(), new OpenExistingRoomWidget());
		hablar.addPage(openExistingRoomPresenter, OverlayContainer.ROL);

		hablar.addPageAddedHandler(new PageAddedHandler() {
			@Override
			public void onPageAdded(final PageAddedEvent event) {
				if (event.isType(RoomPresenter.TYPE)) {
					final RoomPresenter roomPage = (RoomPresenter) event.getPage();
					roomPage.addAction(newInviteAction(invitePage));
					new HablarRoomStateManager(mucChatStateManager, roomPage);
				} else if (event.isType(RosterPage.TYPE)) {
					final RosterPage rosterPage = (RosterPage) event.getPage();
					rosterPage.addAction(newOpenRoomAction(openNewRoomPage));
					rosterPage.addAction(newOpenExistingRoomAction(openExistingRoomPresenter));
				}

			}
		}, true);

	}

	private Action<RoomPage> newInviteAction(final InviteToRoomPresenter invitePage) {
		return new SimpleAction<RoomPage>(RoomMessages.msg.inviteToThisGroupChat(), ACTION_ID_INVITE, IconsBundle.bundle.buddyAddIcon()) {
			@Override
			public void execute(final RoomPage target) {
				invitePage.setRoom(target.getRoom());
				invitePage.requestVisibility(Visibility.focused);
			}
		};
	}

	private SimpleAction<RosterPage> newOpenExistingRoomAction(final OpenExistingRoomPresenter page) {
		final String name = RoomMessages.msg.openExistingRoom();
		final SimpleAction<RosterPage> action = new SimpleAction<RosterPage>(name, ACTION_ID_OPENEXISTINGROOM, IconsBundle.bundle.groupChatIcon()) {
			@Override
			public void execute(final RosterPage target) {
				page.requestVisibility(Visibility.focused);
			}
		};
		return action;
	}

	private SimpleAction<RosterPage> newOpenRoomAction(final OpenNewRoomPresenter page) {
		final String name = RoomMessages.msg.openNewGroupChatTooltip();
		final SimpleAction<RosterPage> action = new SimpleAction<RosterPage>(name, ACTION_ID_OPENROOM, IconsBundle.bundle.groupChatAddIcon()) {
			@Override
			public void execute(final RosterPage target) {
				page.requestVisibility(Visibility.focused);
			}
		};
		return action;
	}

}
