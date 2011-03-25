package com.calclab.hablar.usergroups.client.changegroups;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface ManageGroupsDisplay extends Display {

    GroupSelectorDisplay addGroupSelector();

    void clearGroupList();

    HasClickHandlers getApply();

    HasClickHandlers getCancel();

    HasText getContactNameField();

    HasClickHandlers getNewGroup();

}
