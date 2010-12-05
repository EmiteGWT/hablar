package com.calclab.hablar.groupchat.client;

import static com.calclab.hablar.groupchat.client.HablarGroupChat.i18n;

import com.calclab.emite.core.client.events.StateChangedEvent;
import com.calclab.emite.core.client.events.StateChangedHandler;
import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.chat.ChatProperties;
import com.calclab.emite.im.client.chat.Chat.ChatStates;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.im.client.roster.XmppRoster;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.emite.xep.muc.client.RoomManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.rooms.client.open.EditRoomPresenter;
import com.calclab.hablar.rooms.client.open.EditRoomWidget;

public class ConvertToGroupChatPresenter extends EditRoomPresenter {

    private static final String TYPE = "ConvertToGroupChat";
    private Chat chat;
    private final String roomsService;
    private final XmppSession session;
    private final XmppRoster roster;
    private final ChatManager chatManager;
    private final RoomManager roomManager;

    public ConvertToGroupChatPresenter(final XmppSession session, final XmppRoster roster,
	    final ChatManager chatManager, final RoomManager roomManager, final String roomsService,
	    final HablarEventBus eventBus, final EditRoomWidget openRoomWidget) {
	super(TYPE, eventBus, openRoomWidget);
	this.session = session;
	this.roster = roster;
	this.chatManager = chatManager;
	this.roomManager = roomManager;
	this.roomsService = roomsService;
	display.setPageTitle(i18n().convertPageTitle());
	display.setAcceptText(i18n().convertToGroupAction());
    }

    @Override
    protected void onAccept() {

	final XmppURI currentJid = session.getCurrentUserURI().getJID();
	final String roomName = display.getRoomName().getValue();
	final XmppURI roomUri = XmppURI.uri(roomName, roomsService, currentJid.getNode());

	final ChatProperties newProperties = new ChatProperties(roomUri, chat.getProperties());

	final Room room = (Room) roomManager.openChat(newProperties, true);

	room.addChatStateChangedHandler(true, new StateChangedHandler() {
	    @Override
	    public void onStateChanged(final StateChangedEvent event) {
		if (event.is(ChatStates.ready)) {
		    sendInvitations(room);
		    chatManager.close(chat);
		}
	    }
	});
    }

    @Override
    protected void onPageOpen() {

	final XmppURI currentJid = session.getCurrentUserURI().getJID();
	final XmppURI chatJid = chat.getURI();
	String name = i18n().defaultRoomName(currentJid.getNode(), chatJid.getNode());
	name = name.replaceAll("[^-a-zA-Z0-9 ]", "-");
	display.getRoomName().setValue(name);

	for (final RosterItem item : roster.getItems()) {
	    if (chatJid.equals(item.getJID())) {
		createItem(item, true);
	    } else {
		createItem(item, false);
	    }
	}
    }

    public void setChat(final Chat chat) {
	this.chat = chat;
    }
}
