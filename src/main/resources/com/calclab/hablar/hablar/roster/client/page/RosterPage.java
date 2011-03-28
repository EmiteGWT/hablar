package com.calclab.hablar.roster.client.page;

import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.roster.client.groups.RosterGroupPresenter;
import com.calclab.hablar.roster.client.groups.RosterItemPresenter;

public interface RosterPage extends Page<RosterDisplay> {
    public static final String TYPE = "Roster";

    void addHighPriorityActions();

    void addLowPriorityActions();

    void addAction(Action<RosterPage> action);

    Menu<RosterGroupPresenter> getGroupMenu();

    Menu<RosterItemPresenter> getItemMenu();

}
