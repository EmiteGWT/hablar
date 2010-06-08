package com.calclab.hablar.rooms.client.notification;

import static com.calclab.hablar.rooms.client.HablarRooms.i18n;

import com.calclab.emite.xep.muc.client.Occupant;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.hablar.chat.client.ui.ChatDisplay;
import com.calclab.hablar.rooms.client.room.RoomDisplay;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;

public class RoomNotificationPresenter {

    private final RoomDisplay display;

    public RoomNotificationPresenter(final Room room, final RoomDisplay display) {
	this.display = display;

	room.onOccupantAdded(new Listener<Occupant>() {
	    @Override
	    public void onEvent(final Occupant occupant) {
		showMessage(i18n().hasJoined(occupant.getNick()));
	    }
	});

	room.onOccupantRemoved(new Listener<Occupant>() {
	    @Override
	    public void onEvent(final Occupant occupant) {
		showMessage(i18n().hasLeft(occupant.getNick()));
	    }
	});
    }

    public void showMessage(final String text) {
	// FIXME Duplicate code in RoomPresenter. Maybe is better to use this
	// and better not to use Document/Element in a presenter...
	final Document doc = Document.get();
	final Element element = doc.createSpanElement();
	element.appendChild(doc.createTextNode(text));
	display.addMessage(null, element, ChatDisplay.MessageType.info);
    }
}
