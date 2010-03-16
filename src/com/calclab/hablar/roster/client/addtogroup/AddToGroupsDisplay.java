package com.calclab.hablar.roster.client.addtogroup;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;

public interface AddToGroupsDisplay extends Display {

    GroupSelectorDisplay addGroupSelector(String name, boolean editable, boolean selected);

    void clearGroupList();

    HasClickHandlers getApply();

    HasClickHandlers getCancel();

    HasClickHandlers getNewGroup();

}
