package com.calclab.hablar.roster.client.page;

import com.calclab.hablar.roster.client.ui.groups.GroupHeaderDisplay;
import com.calclab.hablar.roster.client.ui.groups.GroupHeaderWidget;
import com.calclab.hablar.roster.client.ui.groups.RosterGroupDisplay;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;

public class RosterListWidget extends FlowPanel implements RosterListDisplay {
    public RosterListWidget() {
	ensureDebugId("hablar-RosterListWidget");
    }

    @Override
    public void add(final GroupHeaderDisplay headerDisplay, final RosterGroupDisplay groupDisplay) {
	add(headerDisplay.asWidget());
	add(groupDisplay.asWidget());
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public GroupHeaderDisplay createGroupHeader(final String groupId) {
	return new GroupHeaderWidget(groupId);
    }

}
