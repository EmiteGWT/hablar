package com.calclab.hablar.roster.client.page;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.ui.menu.MenuDisplay;
import com.calclab.hablar.roster.client.ui.groups.RosterGroupDisplay;
import com.calclab.hablar.roster.client.ui.groups.RosterGroupPresenter;
import com.calclab.hablar.roster.client.ui.groups.RosterItemDisplay;
import com.google.gwt.event.dom.client.ClickHandler;

public interface RosterDisplay extends Display {

    void addAction(String iconStyle, String debugId, ClickHandler clickHandler);

    void addGroup(RosterGroupPresenter group);

    RosterGroupDisplay newRosterGroupDisplay();

    MenuDisplay<RosterItem> newRosterItemMenuDisplay(String menuId);

    void remove(RosterItemDisplay itemDisplay);

    void setActive(boolean active);

}
