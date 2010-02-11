package com.calclab.hablar.roster.client.page;

import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.roster.client.ui.groups.RosterGroupPresenter;

public class RosterListPresenter implements Presenter<RosterListDisplay> {

    private final RosterListDisplay display;

    public RosterListPresenter(final RosterListDisplay display) {
	this.display = display;
    }

    public void add(final RosterGroupPresenter group) {
	final String groupId = group.getGroupName().replace(' ', '_');
	display.add(group.getGroupLabel(), groupId, group.getDisplay());
    }

    @Override
    public RosterListDisplay getDisplay() {
	return display;
    }

}
