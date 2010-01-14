package com.calclab.hablar.client.roster;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.client.ui.page.Page;

public interface RosterView extends Page {
    RosterItemView addItem(RosterItem item);

    void setActive(boolean active);
}
