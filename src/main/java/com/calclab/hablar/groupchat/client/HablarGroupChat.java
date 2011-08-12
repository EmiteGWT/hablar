package com.calclab.hablar.groupchat.client;

import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.emite.xep.muc.client.RoomManager;
import com.calclab.hablar.chat.client.ui.PairChatPage;
import com.calclab.hablar.chat.client.ui.PairChatPresenter;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.Idify;
import com.calclab.hablar.core.client.container.PageAddedEvent;
import com.calclab.hablar.core.client.container.PageAddedHandler;
import com.calclab.hablar.core.client.container.overlay.OverlayContainer;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.ui.menu.SimpleAction;
import com.calclab.hablar.icons.client.IconsBundle;
import com.calclab.hablar.rooms.client.HablarRoomsConfig;
import com.calclab.hablar.rooms.client.open.EditRoomWidget;
import com.calclab.hablar.roster.client.groups.RosterGroupPresenter;
import com.calclab.hablar.roster.client.page.RosterPage;

/**
 * This object installs the GroupChat functionallity in Hablar.
 * 
 * Group chat are anonymous rooms created by-request using normal chats
 */
public class HablarGroupChat {
	public static final String ACTION_ID_CONVERT = "HablarGroupChat-convertToGroup-";
	public static final String ACTION_ID_OPEN = "HablarGroupChat-openGroupChatAction";

	private final Hablar hablar;
	private final XmppSession session;
	private final RoomManager roomManager;
	private final XmppRoster roster;
	private final ChatManager chatManager;
	private final HablarRoomsConfig config;

	public HablarGroupChat(final Hablar hablar, final HablarRoomsConfig config, final XmppSession session, final XmppRoster roster,
			final ChatManager chatManager, final RoomManager roomManager) {
		this.hablar = hablar;
		this.config = config;
		this.session = session;
		this.roster = roster;
		this.chatManager = chatManager;
		this.roomManager = roomManager;
		install();
	}

	private SimpleAction<PairChatPage> createConvertToGroupChatAction(final XmppURI uri, final ConvertToGroupChatPresenter convertToGroupPage) {
		final String actionId = ACTION_ID_CONVERT + Idify.id(uri);
		return new SimpleAction<PairChatPage>(GroupChatMessages.msg.convertToGroupAction(), actionId, IconsBundle.bundle.buddyAddIcon()) {
			@Override
			public void execute(final PairChatPage chatPage) {
				convertToGroupPage.setChat(chatPage.getChat());
				convertToGroupPage.requestVisibility(Visibility.focused);
			}
		};
	}

	private void install() {
		final OpenGroupChatPresenter openGroupPage = newOpenGroupChatPresenter(config.roomsService);
		hablar.addPage(openGroupPage, OverlayContainer.ROL);
		final ConvertToGroupChatPresenter convertToGroupPage = newConvertToGroupChatPresenter(config.roomsService);
		hablar.addPage(convertToGroupPage, OverlayContainer.ROL);

		hablar.addPageAddedHandler(new PageAddedHandler() {
			@Override
			public void onPageAdded(final PageAddedEvent event) {
				if (event.isType(PairChatPresenter.TYPE)) {
					final PairChatPage chatPage = (PairChatPage) event.getPage();
					final XmppURI uri = chatPage.getChat().getURI();
					chatPage.addAction(createConvertToGroupChatAction(uri, convertToGroupPage));
				} else if (event.isType(RosterPage.TYPE)) {
					final RosterPage roster = (RosterPage) event.getPage();
					roster.getGroupMenu().addAction(newOpenGroupChatAction(openGroupPage));
				}
			}
		}, true);
	}

	public ConvertToGroupChatPresenter newConvertToGroupChatPresenter(final String roomsService) {
		return new ConvertToGroupChatPresenter(session, roster, chatManager, roomManager, roomsService, hablar.getEventBus(), new EditRoomWidget());
	}

	private SimpleAction<RosterGroupPresenter> newOpenGroupChatAction(final OpenGroupChatPresenter openGroupPage) {
		return new SimpleAction<RosterGroupPresenter>(GroupChatMessages.msg.openGroupChatAction(), ACTION_ID_OPEN, IconsBundle.bundle.groupChatIcon()) {
			@Override
			public void execute(final RosterGroupPresenter target) {
				openGroupPage.setGroupName(target.getGroupName());
				openGroupPage.requestVisibility(Visibility.focused);
			}
		};
	}

	public OpenGroupChatPresenter newOpenGroupChatPresenter(final String roomsService) {
		return new OpenGroupChatPresenter(session, roomManager, roster, roomsService, hablar.getEventBus(), new EditRoomWidget());
	}

}
