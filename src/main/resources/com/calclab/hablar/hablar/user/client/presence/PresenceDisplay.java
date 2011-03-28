package com.calclab.hablar.user.client.presence;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.ui.menu.MenuDisplay;
import com.google.gwt.event.dom.client.HasBlurHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface PresenceDisplay extends Display {

    HasClickHandlers getIcon();

    HasClickHandlers getMenu();

    HasKeyDownHandlers getStatus();

    HasBlurHandlers getStatusFocus();

    HasText getStatusText();

    MenuDisplay<PresencePage> newStatusMenuDisplay(String menuId);

    void setPageTitle(String title);

    void setStatusEnabled(boolean enabled);

    void setStatusFocused(boolean focused);

    void setStatusIcon(String icon);

}
