package com.calclab.hablar.user.client.presence;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.ui.menu.MenuDisplay;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface PresenceDisplay extends Display {

    void focusInStatus();

    HasClickHandlers getMenuAction();

    HasKeyDownHandlers getStatus();

    HasText getStatusText();

    MenuDisplay<PresencePage> newStatusMenuDisplay(String menuId);

    void setStatusIcon(String iconStyle);

}
