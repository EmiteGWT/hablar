package com.calclab.hablar.roster.client.page;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.calclab.hablar.core.client.ui.menu.Menu;

public interface RosterPage extends Page<RosterDisplay> {
    public static final String TYPE = "Roster";

    void addAction(Action<RosterPage> action);

    Menu<RosterItem> getItemMenu();

}
