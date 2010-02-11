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
    public void add(final String groupName, final String groupId, final RosterGroupDisplay groupDisplay) {
	final GroupHeaderDisplay headerDisplay = new GroupHeaderWidget(groupId);
	headerDisplay.getName().setText(groupName);
	add(headerDisplay.asWidget());
	add(groupDisplay.asWidget());
    }

    @Override
    public Widget asWidget() {
	return this;
    }

}
