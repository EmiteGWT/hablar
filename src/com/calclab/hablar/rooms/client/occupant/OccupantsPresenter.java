package com.calclab.hablar.rooms.client.occupant;

import static com.calclab.hablar.rooms.client.HablarRooms.i18n;

import com.calclab.emite.xep.muc.client.Occupant;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class OccupantsPresenter implements Presenter<OccupantsDisplay> {

    private final OccupantsDisplay display;
    private int occupantsCount;

    public OccupantsPresenter(final Room room, final OccupantsDisplay display) {
	this.display = display;
	occupantsCount = 0;
	showOccupantsCount();

	room.onOccupantAdded(new Listener<Occupant>() {
	    @Override
	    public void onEvent(final Occupant occupant) {
		occupantsCount++;
		showOccupantsCount();
	    }
	});

	room.onOccupantRemoved(new Listener<Occupant>() {
	    @Override
	    public void onEvent(final Occupant parameter) {
		occupantsCount--;
		showOccupantsCount();
	    }
	});

	display.getAction().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		display.clearPanel();
		for (final Occupant occupant : room.getOccupants()) {
		    final OccupantDisplay ocDisplay = display.addOccupant();
		    ocDisplay.getName().setText(occupant.getNick());
		    ocDisplay.setIcon(HablarIcons.get(IconType.buddyOn));
		}
		display.setPanelVisible(true);
	    }
	});
    }

    @Override
    public OccupantsDisplay getDisplay() {
	return display;
    }

    private void showOccupantsCount() {
	display.getLabel().setText(i18n().occupants(occupantsCount));
    }

}
