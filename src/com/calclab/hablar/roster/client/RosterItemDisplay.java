package com.calclab.hablar.roster.client;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface RosterItemDisplay extends Display {

    HasClickHandlers getAction();

    HasText getJid();

    HasClickHandlers getMenuAction();

    HasText getName();

    HasText getStatus();

    void setIcon(String iconStyle);

    void setStatusVisible(boolean visible);

}
