package com.calclab.hablar.roster.client.page;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.roster.client.ui.groups.GroupHeaderDisplay;
import com.calclab.hablar.roster.client.ui.groups.RosterGroupDisplay;

public interface RosterListDisplay extends Display {

    void add(GroupHeaderDisplay headerDisplay, RosterGroupDisplay groupDisplay);

    GroupHeaderDisplay createGroupHeader(String groupId);

}
