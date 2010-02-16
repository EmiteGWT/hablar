package com.calclab.hablar.rooms.client.occupant;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.user.client.ui.HasText;

public interface OccupantDisplay extends Display {

    HasText getName();

    void setIcon(String iconClass);

}
