package com.calclab.hablar.rooms.client.occupant;

import com.calclab.emite.xep.muc.client.Occupant;
import com.calclab.emite.xep.muc.client.Room;
import com.calclab.emite.xep.muc.client.events.OccupantChangedEvent;
import com.calclab.emite.xep.muc.client.events.OccupantChangedHandler;
import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.icons.client.IconsBundle;
import com.calclab.hablar.rooms.client.RoomMessages;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
import com.google.gwt.user.client.ui.HasText;

/**
 * Shows the list of the occupants of the rooms
 */
public class OccupantsPresenter implements Presenter<OccupantsDisplay> {

	private final OccupantsDisplay display;
	private int occupantsCount;

	public OccupantsPresenter(final Room room, final OccupantsDisplay display) {
		this.display = display;
		occupantsCount = 0;
		updateOccupants(room);

		room.addOccupantChangedHandler(new OccupantChangedHandler() {
			@Override
			public void onOccupantChanged(final OccupantChangedEvent event) {
				if (event.isAdded()) {
					occupantsCount++;
					updateOccupants(room);
				} else if (event.isRemoved()) {
					occupantsCount--;
					updateOccupants(room);
				}
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

	private void addOccupantsToPanel(final Room room) {
		for (final Occupant occupant : room.getOccupants()) {
			final OccupantDisplay ocDisplay = display.addOccupant();
			ocDisplay.getName().setText(occupant.getNick());
			ocDisplay.setIcon(IconsBundle.bundle.buddyIconOn());
		}
	}

	@Override
	public OccupantsDisplay getDisplay() {
		return display;
	}

	private void updateOccupants(final Room room) {
		final HasText label = display.getLabel();
		label.setText(RoomMessages.msg.occupants(occupantsCount));
		display.clearPanel();
		addOccupantsToPanel(room);
	}

}
