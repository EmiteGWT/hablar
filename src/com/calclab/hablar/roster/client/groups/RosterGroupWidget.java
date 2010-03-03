package com.calclab.hablar.roster.client.groups;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class RosterGroupWidget extends FlowPanel implements RosterGroupDisplay {

    public RosterGroupWidget() {
	setStyleName("hablar-RosterGroupWidget");
    }

    @Override
    public void add(final RosterItemDisplay itemDisplay) {
	add(itemDisplay.asWidget());
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public RosterItemDisplay newRosterItemDisplay(final String itemId) {
	return new RosterItemWidget(itemId);
    }

    @Override
    public void remove(final RosterItemDisplay itemDisplay) {
	remove(itemDisplay.asWidget());
    }

    @Override
    public void removeAll() {
	removeAll();
    }

}
