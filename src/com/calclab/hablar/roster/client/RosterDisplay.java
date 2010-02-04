package com.calclab.hablar.roster.client;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.ClickHandler;

public interface RosterDisplay extends Display {

    void add(RosterItemDisplay itemDisplay);

    void addAction(String iconStyle, String debugId, ClickHandler clickHandler);

    RosterItemDisplay newRosterItemDisplay();

    void remove(RosterItemDisplay itemDisplay);

    void setActive(boolean active);

}
