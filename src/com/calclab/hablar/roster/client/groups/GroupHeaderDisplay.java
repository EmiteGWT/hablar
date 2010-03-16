package com.calclab.hablar.roster.client.groups;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface GroupHeaderDisplay extends Display {

    void addStyleName(String styleName);

    HasText getName();

    HasClickHandlers getOpenMenu();

    HasClickHandlers getToggleVisibility();

    void removeStyleName(String styleName);

    void setMenuVisible(boolean visible);
}
