package com.calclab.hablar.openroom.client;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.xep.muc.client.RoomManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.suco.client.Suco;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class OpenRoomPage extends PagePresenter<OpenRoomDisplay> {

    private static final String TYPE = "OpenRoom";
    private static int id = 0;

    public OpenRoomPage(final HablarEventBus eventBus, final String roomService, final OpenRoomDisplay display) {
	super(TYPE, "" + ++id, eventBus, display);

	final RoomManager manager = Suco.get(RoomManager.class);
	final Session session = Suco.get(Session.class);

	display.getCancel().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		requestVisibility(Visibility.hidden);
	    }
	});

	display.getOpen().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		final String node = display.getRoomName().getText().trim();
		if (node.length() > 0) {
		    final String resource = session.getCurrentUser().getNode();
		    final XmppURI roomJID = XmppURI.uri(node, roomService, resource);
		    manager.open(roomJID);
		}
		requestVisibility(Visibility.hidden);
	    }
	});
    }

}
