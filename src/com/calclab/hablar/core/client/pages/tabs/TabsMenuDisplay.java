package com.calclab.hablar.core.client.pages.tabs;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.ui.menu.MenuDisplay;
import com.google.gwt.event.dom.client.HasClickHandlers;

public interface TabsMenuDisplay extends Display {

    HasClickHandlers getMenu();

    MenuDisplay<TabsMenuPage> newTabsMenu(String menuId);

    void setVisible(boolean visible);

}
