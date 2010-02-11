package com.calclab.hablar.rooms.client;

import java.util.HashMap;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.chat.Chat;
import com.calclab.emite.xep.muc.client.RoomManager;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.rooms.client.ui.RoomDisplay;
import com.calclab.hablar.rooms.client.ui.RoomPage;
import com.calclab.hablar.rooms.client.ui.RoomWidget;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;

public class HablarRoomManager {

    public static interface RoomPageFactory {
	RoomDisplay create(boolean sendButtonVisible);
    }

    private final Hablar hablar;
    private final RoomPageFactory factory;
    private final HashMap<XmppURI, RoomPage> roomPages;
    private final HablarRoomsConfig config;

    public HablarRoomManager(final Hablar hablar, final HablarRoomsConfig config) {
	this(hablar, config, new RoomPageFactory() {
	    @Override
	    public RoomDisplay create(final boolean sendButtonVisible) {
		return new RoomWidget(sendButtonVisible);
	    }
	});
    }

    public HablarRoomManager(final Hablar hablar, final HablarRoomsConfig config, final RoomPageFactory factory) {
	this.hablar = hablar;
	this.config = config;
	this.factory = factory;
	roomPages = new HashMap<XmppURI, RoomPage>();
	final RoomManager rooms = Suco.get(RoomManager.class);

	rooms.onChatCreated(new Listener<Chat>() {
	    @Override
	    public void onEvent(final Chat chat) {
		createRoom(chat);
	    }
	});

	rooms.onChatOpened(new Listener<Chat>() {
	    @Override
	    public void onEvent(final Chat chat) {
		final RoomPage page = roomPages.get(chat.getURI());
		assert page != null;
		page.requestVisibility(Visibility.focused);
	    }
	});
    }

    protected void createRoom(final Chat chat) {
	final RoomDisplay display = factory.create(true);
	final RoomPage presenter = new RoomPage(hablar.getEventBus(), chat, display);
	roomPages.put(chat.getURI(), presenter);
	hablar.addPage(presenter);
    }

}
