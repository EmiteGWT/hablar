package com.calclab.hablar.client.roster;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.client.ui.menu.MenuAction;
import com.calclab.hablar.client.ui.menu.PopupMenuView;
import com.calclab.hablar.client.ui.page.Page;

public interface RosterView extends Page {
    RosterItemView createItemView();

    PopupMenuView<RosterItem> createMenu(MenuAction<RosterItem>... actions);

    void setActive(boolean active);
}
