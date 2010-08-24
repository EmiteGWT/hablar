package com.calclab.hablar.rooms.client.existing;

import java.util.List;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.xep.muc.client.RoomManager;
import com.calclab.emite.xep.mucdisco.client.ExistingRoom;
import com.calclab.emite.xep.mucdisco.client.ExistingRoomsCallback;
import com.calclab.emite.xep.mucdisco.client.RoomDiscoveryManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.suco.client.Suco;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class OpenExistingRoomPresenter extends PagePresenter<OpenExistingRoomDisplay> {

    public static final String TYPE = "OpenExistingRoom";
    private final String roomsService;
    private final Session session;

    public OpenExistingRoomPresenter(final String roomsService, final HablarEventBus eventBus,
	    final OpenExistingRoomDisplay display) {
	super(TYPE, eventBus, display);
	session = Suco.get(Session.class);
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

    @Override
    protected void onBeforeFocus() {
	final RoomDiscoveryManager discoManager = Suco.get(RoomDiscoveryManager.class);

	discoManager.discoverRooms(XmppURI.uri(roomsService), new ExistingRoomsCallback() {
	    @Override
	    public void onExistingRooms(final List<ExistingRoom> roomItems) {
		display.setRooms(roomItems);
	    }
	});

    }

    private void onAccept() {
	final RoomManager rooms = Suco.get(RoomManager.class);
	final XmppURI roomUri = display.getSelectedRoom();
	final XmppURI user = session.getCurrentUser();
	final XmppURI openRoomUri = XmppURI.uri(roomUri.getNode(), roomUri.getHost(), user
		.getNode());
	rooms.open(openRoomUri);
    }
}
