package com.calclab.hablar.roster.client.groups;

import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class RosterGroupWidget extends FlowPanel implements RosterGroupDisplay {

    public RosterGroupWidget() {
	addStyleName("hablar-RosterGroupWidget");
    }

    @Override
    public void add(final RosterItemDisplay itemDisplay) {
	add(itemDisplay.asWidget());
    }

	@Override
	public void add(RosterItemDisplay itemDisplay, RosterItemDisplay beforeItem) {
		int index = getWidgetIndex(beforeItem.asWidget());
		
		if(index > -1) {
			insert(itemDisplay.asWidget(), index);
		} else {
			add(itemDisplay);
		}
	}

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public RosterItemDisplay newRosterItemDisplay(final String groupId, final String itemId) {
	return new RosterItemWidget(groupId, itemId);
    }

    @Override
    public void remove(final RosterItemDisplay itemDisplay) {
	remove(itemDisplay.asWidget());
    }

    @Override
    public void removeAll() {
	clear();
    }

}
