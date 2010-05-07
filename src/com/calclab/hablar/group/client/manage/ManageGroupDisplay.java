package com.calclab.hablar.group.client.manage;

import java.util.Collection;
import java.util.List;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.ui.selectionlist.Selectable;
import com.calclab.hablar.core.client.validators.HasState;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;

/**
 * Interface for a form to manage a group in the roster.
 */
public interface ManageGroupDisplay extends Display {

    HasClickHandlers getApply();

    HasClickHandlers getCancel();

    void clearSelectionList();

    void addRosterItem(RosterItem rosterItem);

    void addSelectedRosterItem(RosterItem rosterItem);

    String getGroupNameText();

    HasValue<String> getGroupName();

    HasValue<List<Selectable>> getSelectionList();

    HasText getGroupNameError();

    HasKeyDownHandlers getGroupNameKeys();

    Collection<RosterItem> getSelectedItems();

    HasState<Boolean> getAcceptEnabled();

    void setPageTitle(String title);
}
