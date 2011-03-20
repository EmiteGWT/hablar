package com.calclab.hablar.roster.client.groups;

import com.calclab.hablar.core.client.mvp.Display;

public interface RosterGroupDisplay extends Display {

	/**
	 * Adds a {@link RosterItemDisplay} at the end of the group
	 * @param itemDisplay
	 */
    void add(RosterItemDisplay itemDisplay);

    /**
     * Adds a {@link RosterItemDisplay} before the specified item
     * @param itemDisplay
     * @param beforeItem
     */
    void add(RosterItemDisplay itemDisplay, RosterItemDisplay beforeItem);

    boolean isVisible();

    RosterItemDisplay newRosterItemDisplay(String groupId, String itemId);

    void remove(RosterItemDisplay itemDisplay);

    void removeAll();

    void setVisible(boolean visible);

}
