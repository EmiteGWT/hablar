package com.calclab.hablar.roster.client.groups;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.HasText;

public interface RosterItemDisplay extends Display {

    HasClickHandlers getAction();

    HasText getJid();

    HasClickHandlers getMenuAction();

    HasText getName();

    HasText getStatus();

    void setIcon(ImageResource icon);

    void setStatusVisible(boolean visible);

    void setMenuVisible(boolean visible);
}
