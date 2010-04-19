package com.calclab.hablar.rooms.client.occupant;

import static com.calclab.hablar.rooms.client.HablarRooms.i18n;

import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.xep.muc.client.Occupant;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.PresenceIcon;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;

/**
 * Shows the list of the occupants of the rooms
 */
public class OccupantsPresenter implements Presenter<OccupantsDisplay> {

    private final OccupantsDisplay display;
    private int occupantsCount;
    private final Roster roster;

    public OccupantsPresenter(final Room room, final OccupantsDisplay display) {
	this.display = display;
	occupantsCount = 0;
	updateOccupants(room);

	roster = Suco.get(Roster.class);

	room.onOccupantAdded(new Listener<Occupant>() {
	    @Override
	    public void onEvent(final Occupant occupant) {
		occupantsCount++;
		updateOccupants(room);
	    }
	});

	room.onOccupantRemoved(new Listener<Occupant>() {
	    @Override
	    public void onEvent(final Occupant parameter) {
		occupantsCount--;
		updateOccupants(room);
	    }
	});

	display.getOverAction().addMouseOverHandler(new MouseOverHandler() {
	    @Override
	    public void onMouseOver(final MouseOverEvent event) {
		display.setPanelVisible(true);
	    }
	});

	display.getOutAction().addMouseOutHandler(new MouseOutHandler() {
	    @Override
	    public void onMouseOut(final MouseOutEvent event) {
		display.setPanelVisible(false);
	    }
	});
    }

    @Override
    public OccupantsDisplay getDisplay() {
	return display;
    }

    private void addOccupantsToPanel(final Room room) {
	for (final Occupant occupant : room.getOccupants()) {
	    final OccupantDisplay ocDisplay = display.addOccupant();
	    ocDisplay.getName().setText(occupant.getNick());
	    final RosterItem item = roster.getItemByJID(occupant.getURI());
	    final boolean available = item != null && item.isAvailable();
	    String icon = PresenceIcon.getIcon(available, occupant.getShow());
	    icon = HablarIcons.get(IconType.buddyOn);
	    ocDisplay.setIcon(icon);
	}
    }

    private void updateOccupants(final Room room) {
	display.getLabel().setText(i18n().occupants(occupantsCount));
	display.clearPanel();
	addOccupantsToPanel(room);
    }

}
