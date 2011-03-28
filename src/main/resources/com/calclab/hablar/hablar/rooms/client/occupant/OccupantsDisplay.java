package com.calclab.hablar.rooms.client.occupant;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasMouseOutHandlers;
import com.google.gwt.event.dom.client.HasMouseOverHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface OccupantsDisplay extends Display {
    OccupantDisplay addOccupant();

    void clearPanel();

    HasClickHandlers getAction();

    HasText getLabel();

    HasMouseOutHandlers getOutAction();

    HasMouseOverHandlers getOverAction();

    boolean isPanelVisible();

    void setPanelVisible(boolean visible);

}
