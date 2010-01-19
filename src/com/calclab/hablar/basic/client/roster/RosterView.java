package com.calclab.hablar.basic.client.roster;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.basic.client.ui.menu.MenuAction;
import com.calclab.hablar.basic.client.ui.menu.PopupMenuView;
import com.calclab.hablar.basic.client.ui.page.PageView;
import com.google.gwt.event.dom.client.ClickHandler;

public interface RosterView extends PageView {
    void addAction(String iconStyle, String debugId, ClickHandler clickHandler);

    RosterItemView createItemView();

    PopupMenuView<RosterItem> createMenu(String debugId, MenuAction<RosterItem>... actions);

    void removeItemView(RosterItemView view);

    void setActive(boolean active);
}
