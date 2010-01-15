package com.calclab.hablar.client.roster;

public interface RosterItemView {

    RosterItemIcons getIconStyles();

    void setIcon(String iconStyle);

    void setJID(String string);

    void setName(String name);

    void setStatus(String status);

    void setStatusVisible(boolean b);
}
