package com.calclab.hablar.roster.client;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.basic.client.ui.menu.PopupMenuView;
import com.calclab.hablar.basic.client.ui.page.PageView;
import com.google.gwt.event.dom.client.ClickHandler;

public interface RosterView extends PageView {
    public static final String TYPE = "Roster";

    void addAction(String iconStyle, String debugId, ClickHandler clickHandler);

    RosterItemView createItemView();

    PopupMenuView<RosterItem> getItemMenu();

    void removeItemView(RosterItemView view);

    void setActive(boolean active);
}
