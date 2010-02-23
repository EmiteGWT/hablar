package com.calclab.hablar.roster.client.page;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.core.client.ui.menu.MenuDisplay;
import com.calclab.hablar.roster.client.groups.RosterGroupDisplay;
import com.calclab.hablar.roster.client.groups.RosterGroupPresenter;
import com.calclab.hablar.roster.client.groups.RosterItemPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;

public interface RosterDisplay extends Display {

    void addGroup(RosterGroupPresenter group, Menu<RosterGroupPresenter> menu);

    HasClickHandlers createAction(Action<?> action);

    RosterGroupDisplay newRosterGroupDisplay();

    MenuDisplay<RosterGroupPresenter> newRosterGroupMenuDisplay(String menuId);

    MenuDisplay<RosterItemPresenter> newRosterItemMenuDisplay(String menuId);

    void remove(RosterGroupPresenter groupPresenter);

    void setActive(boolean active);

}
