package com.calclab.hablar.client.roster;

import com.calclab.hablar.client.ui.page.Page;

public interface RosterView extends Page {
    RosterItemView createItemView();

    void setActive(boolean active);
}
