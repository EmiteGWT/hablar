package com.calclab.hablar.roster.client.page;

import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.roster.client.ui.groups.RosterGroupPresenter;
import com.calclab.hablar.roster.client.ui.groups.RosterItemPresenter;

public interface RosterPage extends Page<RosterDisplay> {
    public static final String TYPE = "Roster";

    void addAction(Action<RosterPage> action);

    Menu<RosterGroupPresenter> getGroupMenu();

    Menu<RosterItemPresenter> getItemMenu();

}
