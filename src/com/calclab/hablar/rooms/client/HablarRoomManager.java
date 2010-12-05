package com.calclab.hablar.rooms.client;

import java.util.ArrayList;
import java.util.HashMap;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.emite.im.client.chat.events.ChatChangedEvent;
import com.calclab.emite.im.client.chat.events.ChatChangedHandler;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.emite.xep.muc.client.RoomInvitation;
import com.calclab.emite.xep.muc.client.RoomManager;
import com.calclab.emite.xep.muc.client.events.RoomInvitationEvent;
import com.calclab.emite.xep.muc.client.events.RoomInvitationHandler;
import com.calclab.hablar.chat.client.ui.ChatMessage;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.rooms.client.room.RoomDisplay;
import com.calclab.hablar.rooms.client.room.RoomPresenter;
import com.calclab.hablar.rooms.client.room.RoomWidget;

public class HablarRoomManager {

    public static interface RoomPageFactory {
	RoomDisplay create(boolean sendButtonVisible);
    }

    public static interface RoomPresenterFactory {
	RoomPresenter create(HablarEventBus eventBus, Room room, RoomDisplay display);
    }

    private final Hablar hablar;
    private final RoomPageFactory factory;
    private final RoomPresenterFactory presenterFactory;
    private final HashMap<XmppURI, RoomPresenter> roomPages;
    private final ArrayList<RoomInvitation> acceptedInvitations;

    public HablarRoomManager(final RoomManager rooms, final Hablar hablar, final HablarRoomsConfig config) {
	this(rooms, hablar, config, new RoomPageFactory() {
	    @Override
	    public RoomDisplay create(final boolean sendButtonVisible) {
		return new RoomWidget(sendButtonVisible);
	    }
	}, new RoomPresenterFactory() {

	    @Override
	    public RoomPresenter create(final HablarEventBus eventBus, final Room room, final RoomDisplay display) {
		return new RoomPresenter(eventBus, room, display);
	    }

	});
    }

    public HablarRoomManager(final RoomManager rooms, final Hablar hablar, final HablarRoomsConfig config,
	    final RoomPageFactory factory, final RoomPresenterFactory presenterFactory) {
	this.hablar = hablar;
	this.factory = factory;
	this.presenterFactory = presenterFactory;
	acceptedInvitations = new ArrayList<RoomInvitation>();
	roomPages = new HashMap<XmppURI, RoomPresenter>();

	rooms.addChatChangedHandler(new ChatChangedHandler() {
	    @Override
	    public void onChatChanged(final ChatChangedEvent event) {
		if (event.isCreated()) {
		    createRoom((Room) event.getChat());
		} else if (event.isOpened()) {
		    openRoom(event.getChat());
		}
	    }
	});

	rooms.addRoomInvitationReceivedHandler(new RoomInvitationHandler() {
	    @Override
	    public void onRoomInvitation(final RoomInvitationEvent event) {
		final RoomInvitation invitation = event.getRoomInvitation();
		acceptedInvitations.add(invitation);
		rooms.acceptRoomInvitation(invitation);
	    }
	});
    }

    protected void createRoom(final Room room) {
	final RoomDisplay display = factory.create(true);
	final RoomPresenter presenter = presenterFactory.create(hablar.getEventBus(), room, display);
	roomPages.put(room.getURI(), presenter);
	hablar.addPage(presenter);
    }

    protected RoomInvitation getInvitation(final XmppURI uri) {
	for (final RoomInvitation invitation : acceptedInvitations) {
	    if (invitation.getRoomURI().getNode().equals(uri.getNode())) {
		return invitation;
	    }
	}
	return null;
    }

    protected void openRoom(final Chat room) {
	final RoomPresenter roomPage = roomPages.get(room.getURI());
	assert roomPage != null : "Error in room pages - HablarRoomManager";
	final RoomInvitation invitation = getInvitation(room.getURI());
	if (invitation != null) {
	    acceptedInvitations.remove(invitation);
	    roomPage.requestVisibility(Visibility.notFocused);
	    String message = "You have been invited to this group chat";

	    if (invitation.getInvitor() != null) {
		message += " by " + invitation.getInvitor().getNode();
	    }

	    message += invitation.getReason() != null ? ": " + invitation.getReason() : "";
	    roomPage.addMessage(new ChatMessage(message));
	} else {
	    roomPage.requestVisibility(Visibility.focused);
	}
    }
}
