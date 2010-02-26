package com.calclab.hablar.roster.client.groups;

import com.calclab.hablar.core.client.mvp.Display;

public interface RosterGroupDisplay extends Display {

    void add(RosterItemDisplay itemDisplay);

    boolean isVisible();

    RosterItemDisplay newRosterItemDisplay();

    void remove(RosterItemDisplay itemDisplay);

    void removeAll();

    void setVisible(boolean visible);

}
