package com.calclab.hablar.user.client.presence;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.ui.menu.MenuDisplay;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface PresenceDisplay extends Display {

    void setStatusFocused(boolean focused);

    HasClickHandlers getIcon();

    HasClickHandlers getMenu();

    HasKeyDownHandlers getStatus();

    HasText getStatusText();

    MenuDisplay<PresencePage> newStatusMenuDisplay(String menuId);

    void setStatusEnabled(boolean enabled);

    void setStatusIcon(String iconStyle);

}
