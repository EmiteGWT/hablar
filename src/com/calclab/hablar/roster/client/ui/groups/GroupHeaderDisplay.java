package com.calclab.hablar.roster.client.ui.groups;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface GroupHeaderDisplay extends Display {

    HasText getName();

    HasClickHandlers getOpenMenu();

    HasClickHandlers getToggleVisibility();

    void setMenuVisible(boolean visible);
}
