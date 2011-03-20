package com.calclab.hablar.roster.client.selection;

import java.util.Collection;

import com.calclab.emite.im.client.roster.RosterItem;

/**
 * It represents everything that can select in a set of roster items.
 */
public interface RosterItemSelector {

    void addRosterItem(RosterItem rosterItem);

    void addSelectedRosterItem(RosterItem rosterItem);

    void clearSelectionList();

    Collection<RosterItem> getSelectedItems();

}