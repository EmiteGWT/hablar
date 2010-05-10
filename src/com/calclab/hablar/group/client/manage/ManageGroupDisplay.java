package com.calclab.hablar.group.client.manage;

import java.util.List;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.ui.selectionlist.Selectable;
import com.calclab.hablar.core.client.validators.HasState;
import com.calclab.hablar.roster.client.selection.RosterItemSelector;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.HasValue;

/**
 * Interface for a form to manage a group in the roster.
 */
public interface ManageGroupDisplay extends Display, RosterItemSelector {

    HasClickHandlers getApply();

    HasClickHandlers getCancel();

    String getGroupNameText();

    HasValue<String> getGroupName();

    HasValue<List<Selectable>> getSelectionList();

    HasText getGroupNameError();

    HasKeyDownHandlers getGroupNameKeys();

    HasState<Boolean> getAcceptEnabled();

    void setPageTitle(String title);
}
