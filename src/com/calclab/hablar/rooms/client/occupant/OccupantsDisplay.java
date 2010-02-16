package com.calclab.hablar.rooms.client.occupant;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface OccupantsDisplay extends Display {
    OccupantDisplay addOccupant();

    void clearPanel();

    HasClickHandlers getAction();

    HasText getLabel();

    void setPanelVisible(boolean visible);

}
