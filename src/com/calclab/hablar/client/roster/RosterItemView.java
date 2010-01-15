package com.calclab.hablar.client.roster;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.client.ui.icons.Icons.IconType;

public interface RosterItemView {

    void setIcon(IconType icon);

    void setItem(RosterItem item);

    void setJID(String string);

    void setName(String name);

    void setStatus(String status);

    void setStatusVisible(boolean b);
}
