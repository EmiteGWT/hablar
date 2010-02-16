package com.calclab.hablar.groupchat.client;

import static com.calclab.hablar.groupchat.client.HablarGroupChat.i18n;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.emite.im.client.chat.ChatManager;
import com.calclab.emite.im.client.chat.Chat.State;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.emite.xep.muc.client.RoomManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.rooms.client.open.OpenRoomPresenter;
import com.calclab.hablar.rooms.client.open.OpenRoomWidget;
import com.calclab.hablar.rooms.client.open.SelectRosterItemPresenter;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;

public class ConvertToGroupChatPresenter extends OpenRoomPresenter {

    private static final String TYPE = "ConvertToGroupChat";
    private Chat chat;
    private final String roomsService;

    public ConvertToGroupChatPresenter(final String roomsService, final HablarEventBus eventBus,
	    final OpenRoomWidget openRoomWidget) {
	super(TYPE, eventBus, openRoomWidget);
	this.roomsService = roomsService;
	display.setPageTitle(i18n().convertPageTitle());
    }

    public void setChat(final Chat chat) {
	this.chat = chat;
    }

    @Override
    protected void onAccept() {
	final RoomManager rooms = Suco.get(RoomManager.class);
	final Session session = Suco.get(Session.class);
	final ChatManager chats = Suco.get(ChatManager.class);

	final XmppURI currentJid = session.getCurrentUser().getJID();
	final String roomName = display.getRoomName().getText();
	final XmppURI roomUri = XmppURI.uri(roomName, roomsService, currentJid.getNode());
	final Room room = (Room) rooms.open(roomUri);
	room.onStateChanged(new Listener<State>() {
	    @Override
	    public void onEvent(final State parameter) {
		if (parameter == State.ready) {
		    final SelectRosterItemPresenter self = getItem(currentJid);
		    if (self != null) {
			self.setEnabled(false);
		    }
		    sendInvitations(room);
		    chats.close(chat);
		}
	    }
	});
    }

    @Override
    protected void onPageOpen() {

	final Roster roster = Suco.get(Roster.class);
	final Session session = Suco.get(Session.class);

	final XmppURI currentJid = session.getCurrentUser().getJID();
	final XmppURI chatJid = chat.getURI();
	final String name = i18n().defaultRoomName(currentJid.getNode(), chatJid.getNode());
	display.getRoomName().setText(name);

	setItems(roster.getItems(), true, false);
	final SelectRosterItemPresenter other = getItem(chatJid);
	if (other == null) {
	} else {
	    other.setSelected(true);
	    other.setEnabled(false);
	}
	final SelectRosterItemPresenter self = getItem(currentJid);
	if (self != null) {
	    self.setSelected(true);
	    self.setEnabled(false);
	}
    }
}
