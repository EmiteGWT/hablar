package com.calclab.hablar.roster.client.page;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.roster.client.ui.groups.RosterGroupDisplay;

public interface RosterListDisplay extends Display {

    void add(String groupName, String groupId, RosterGroupDisplay display);

}
