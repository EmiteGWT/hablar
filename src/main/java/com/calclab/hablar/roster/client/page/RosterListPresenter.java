package com.calclab.hablar.roster.client.page;

import java.util.HashMap;

import com.calclab.hablar.core.client.mvp.Presenter;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.roster.client.groups.GroupHeaderDisplay;
import com.calclab.hablar.roster.client.groups.GroupHeaderPresenter;
import com.calclab.hablar.roster.client.groups.RosterGroupPresenter;

public class RosterListPresenter implements Presenter<RosterListDisplay> {

    private final RosterListDisplay display;
    private final HashMap<String, GroupHeaderPresenter> nameToHeaders;

    public RosterListPresenter(final RosterListDisplay display) {
	this.display = display;
	nameToHeaders = new HashMap<String, GroupHeaderPresenter>();
    }

    public void add(final RosterGroupPresenter group, final Menu<RosterGroupPresenter> menu) {
	final String groupName = group.getGroupName();
	final String groupId = groupName != null ? groupName.replace(' ', '_') : "all";

	final GroupHeaderDisplay headerDisplay = display.createGroupHeader(groupId);
	final GroupHeaderPresenter header = new GroupHeaderPresenter(group, menu, headerDisplay);
	nameToHeaders.put(groupName, header);
	display.add(headerDisplay, group.getDisplay());
    }

    @Override
    public RosterListDisplay getDisplay() {
	return display;
    }

    public void remove(final RosterGroupPresenter groupPresenter) {
	final GroupHeaderPresenter header = nameToHeaders.get(groupPresenter.getGroupName());
	display.remove(header.getDisplay(), groupPresenter.getDisplay());
    }

}
