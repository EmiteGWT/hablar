package com.calclab.hablar.rooms.client.existing;

import com.calclab.emite.xep.mucdisco.client.ExistingRoom;
import com.calclab.hablar.core.client.ui.selectionlist.Selectable;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Widget;

public class RoomSelectable implements Selectable {
    private final ExistingRoom room;

    private final RoomWidget widget;

    public RoomSelectable(final ExistingRoom room) {
	this.room = room;
	widget = new RoomWidget(room.getName());
    }

    @Override
    public int compareTo(final Selectable o) {
	return room.compareTo(((RoomSelectable) o).room);
    }

    @Override
    public HasClickHandlers getAction() {
	return widget.getAction();
    }

    @Override
    public String getId() {
	return room.getUri().toString();
    }

    @Override
    public Object getItem() {
	return room;
    }

    @Override
    public Widget getWidget() {
	return widget;
    }
}
