package com.calclab.hablar.roster.client.addtogroup;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasFocusHandlers;
import com.google.gwt.event.dom.client.HasKeyPressHandlers;
import com.google.gwt.user.client.ui.HasValue;

public interface AddToGroupDisplay extends Display {

    public static enum Action {
	addToExisting, addToNew
    }

    public void setAcceptEnabled(boolean enabled);

    public void setActionEnabled(Action action, boolean enabled);

    public void setActiveAction(Action action);

    void addToGroupList(String groupName);

    void clearGroupList();

    HasValue<Boolean> getAddToExisting();

    HasValue<Boolean> getAddToNew();

    HasClickHandlers getApply();

    HasClickHandlers getCancel();

    HasFocusHandlers getNewGroupFocus();

    HasKeyPressHandlers getNewGroupKeys();

    HasValue<String> getNewGroupName();

    HasFocusHandlers getSelectGroupFocus();

}
