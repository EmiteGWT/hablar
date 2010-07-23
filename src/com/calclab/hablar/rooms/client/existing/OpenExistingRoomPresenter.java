package com.calclab.hablar.rooms.client.existing;

import java.util.ArrayList;
import java.util.List;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.xep.disco.client.DiscoveryManager;
import com.calclab.emite.xep.disco.client.DiscoveryManager.DiscoveryManagerResponse;
import com.calclab.emite.xep.disco.client.Item;
import com.calclab.emite.xep.muc.client.RoomManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class OpenExistingRoomPresenter extends PagePresenter<OpenExistingRoomDisplay> {

    public static final String TYPE = "OpenExistingRoom";
    private final String roomsService;

    public OpenExistingRoomPresenter(String roomsService, HablarEventBus eventBus, OpenExistingRoomDisplay display) {
	super(TYPE, eventBus, display);
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
	DiscoveryManager discoManager = Suco.get(DiscoveryManager.class);
	final Session session = Suco.get(Session.class);
	final XmppURI user = session.getCurrentUser();
	discoManager.sendDiscoItemsQuery(user, XmppURI.uri(null, roomsService, null),
		new Listener<DiscoveryManager.DiscoveryManagerResponse>() {

		    @Override
		    public void onEvent(DiscoveryManagerResponse parameter) {
			List<ExistingRoom> rooms = new ArrayList<ExistingRoom>();
			List<Item> items = parameter.getItems();
			if (items != null) {
			    for (Item item : items) {
				rooms.add(new ExistingRoom(item.name, XmppURI.uri(item.jid + "/" + user.getNode())));
			    }
			}
			display.setRooms(rooms);
		    }
		});
    }

    private void onAccept() {
	final RoomManager rooms = Suco.get(RoomManager.class);
	final XmppURI roomUri = display.getSelectedRoom();
	rooms.open(roomUri);
    }
}
