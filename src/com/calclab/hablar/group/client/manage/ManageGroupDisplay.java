package com.calclab.hablar.group.client.manage;

import java.util.Collection;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.validators.HasState;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface ManageGroupDisplay extends Display {

    HasClickHandlers getApply();

    HasClickHandlers getCancel();

    void clearSelectionList();

    void addRosterItem(RosterItem rosterItem);

    String getGroupNameText();

    HasText getGroupName();

    HasText getGroupNameError();

    HasKeyDownHandlers getGroupNameKeys();

    Collection<RosterItem> getSelectedItems();

    HasState<Boolean> getAcceptEnabled();
}
