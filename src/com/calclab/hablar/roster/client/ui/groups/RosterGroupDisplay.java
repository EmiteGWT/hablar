package com.calclab.hablar.roster.client.ui.groups;

import com.calclab.hablar.core.client.mvp.Display;

public interface RosterGroupDisplay extends Display {

    void add(RosterItemDisplay itemDisplay);

    RosterItemDisplay newRosterItemDisplay();

}
