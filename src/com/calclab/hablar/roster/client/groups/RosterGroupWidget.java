package com.calclab.hablar.roster.client.groups;

import java.util.HashSet;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class RosterGroupWidget extends FlowPanel implements RosterGroupDisplay {

	/**
	 * Keeps track of the {@link RosterItemDisplay} widgets which this group
	 * contains
	 */
	private final HashSet<RosterItemDisplay> rosterItemDisplays;

	public RosterGroupWidget() {
		addStyleName("hablar-RosterGroupWidget");

		rosterItemDisplays = new HashSet<RosterItemDisplay>();
	}

	@Override
	public void setVisible(final boolean visible) {
		// If this was previously invisible then we will force a re-layout of
		// all the items within it. This is in order to fix Issue #333.
		final boolean forceLayout = (visible && !isVisible());

		super.setVisible(visible);

		if (forceLayout) {
			for (final RosterItemDisplay display : rosterItemDisplays) {
				display.forceLayout();
			}
		}
	}

	@Override
	public void add(final RosterItemDisplay itemDisplay) {
		add(itemDisplay.asWidget());
		rosterItemDisplays.add(itemDisplay);
	}

	@Override
	public void add(RosterItemDisplay itemDisplay, RosterItemDisplay beforeItem) {
		final int index = getWidgetIndex(beforeItem.asWidget());

		if (index > -1) {
			insert(itemDisplay.asWidget(), index);
		} else {
			add(itemDisplay.asWidget());
		}
		rosterItemDisplays.add(itemDisplay);
	}

	@Override
	public Widget asWidget() {
		return this;
	}

	@Override
	public RosterItemDisplay newRosterItemDisplay(final String groupId,
			final String itemId) {
		return new RosterItemWidget(groupId, itemId);
	}

	@Override
	public void remove(final RosterItemDisplay itemDisplay) {
		remove(itemDisplay.asWidget());
		rosterItemDisplays.remove(itemDisplay);
	}

	@Override
	public void removeAll() {
		clear();
		rosterItemDisplays.clear();
	}

}
