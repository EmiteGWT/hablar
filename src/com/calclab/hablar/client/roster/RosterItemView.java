package com.calclab.hablar.client.roster;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.client.ui.styles.HablarStyles.IconType;

public interface RosterItemView {

    RosterItem getItem();

    void setIcon(IconType icon);

    void setItem(RosterItem item);

    void setJID(String string);

    void setName(String name);

    void setSelected(boolean selected);

    void setStatus(String status);

    void setStatusVisible(boolean b);
}
