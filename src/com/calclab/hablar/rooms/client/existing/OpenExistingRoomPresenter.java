package com.calclab.hablar.rooms.client.existing;

import java.util.List;

import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.xep.muc.client.RoomManager;
import com.calclab.emite.xep.mucdisco.client.ExistingRoom;
import com.calclab.emite.xep.mucdisco.client.ExistingRoomsCallback;
import com.calclab.emite.xep.mucdisco.client.RoomDiscoveryManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class OpenExistingRoomPresenter extends PagePresenter<OpenExistingRoomDisplay> {

    public static final String TYPE = "OpenExistingRoom";
    private final String roomsService;
    private final XmppSession session;
    private final RoomManager roomManager;
    private final RoomDiscoveryManager roomDiscoveryManager;

    public OpenExistingRoomPresenter(final XmppSession session, final RoomManager roomManager,
	    final RoomDiscoveryManager roomDiscoveryManager, final String roomsService, final HablarEventBus eventBus,
	    final OpenExistingRoomDisplay display) {
	super(TYPE, eventBus, display);
	this.session = session;
	this.roomManager = roomManager;
	this.roomDiscoveryManager = roomDiscoveryManager;
	this.roomsService = roomsService;
	display.getCancel().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		requestVisibility(Visibility.hidden);
	    }
	});

	display.getAccept().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		onAccept();
		requestVisibility(Visibility.notFocused);
	    }
	});
    }

    private void onAccept() {
	final XmppURI roomUri = display.getSelectedRoom();
	final XmppURI user = session.getCurrentUserURI();
	final XmppURI openRoomUri = XmppURI.uri(roomUri.getNode(), roomUri.getHost(), user.getNode());
	roomManager.open(openRoomUri);
    }

    @Override
    protected void onBeforeFocus() {
	roomDiscoveryManager.discoverRooms(XmppURI.uri(roomsService), new ExistingRoomsCallback() {
	    @Override
	    public void onExistingRooms(final List<ExistingRoom> roomItems) {
		display.setRooms(roomItems);
	    }
	});

    }
}
