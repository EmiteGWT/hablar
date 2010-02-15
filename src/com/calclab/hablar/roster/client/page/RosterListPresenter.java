package com.calclab.hablar.roster.client.page;

import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.roster.client.groups.GroupHeaderDisplay;
import com.calclab.hablar.roster.client.groups.GroupHeaderPresenter;
import com.calclab.hablar.roster.client.groups.RosterGroupPresenter;

public class RosterListPresenter implements Presenter<RosterListDisplay> {

    private final RosterListDisplay display;

    public RosterListPresenter(final RosterListDisplay display) {
	this.display = display;
    }

    public void add(final RosterGroupPresenter group, final Menu<RosterGroupPresenter> menu) {
	final String groupId = group.getGroupName().replace(' ', '_');

	final GroupHeaderDisplay headerDisplay = display.createGroupHeader(groupId);
	new GroupHeaderPresenter(group, menu, headerDisplay);

	display.add(headerDisplay, group.getDisplay());
    }

    @Override
    public RosterListDisplay getDisplay() {
	return display;
    }

}
