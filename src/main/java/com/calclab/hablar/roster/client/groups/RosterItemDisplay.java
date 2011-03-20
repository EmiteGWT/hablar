package com.calclab.hablar.roster.client.groups;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface RosterItemDisplay extends Display {

    void addStyleName(String styleName);

    HasClickHandlers getAction();

    HasText getJid();

    HasClickHandlers getMenuAction();

    HasText getName();

    HasText getStatus();

    void setColor(String color);

    void setIcon(String icon);

    void setMenuVisible(boolean visible);

    void setStatusVisible(boolean visible);

    void setWidgetTitle(String title);
}
