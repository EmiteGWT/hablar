package com.calclab.hablar.editbuddy.client.ui;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.validators.HasState;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasText;

public interface EditBuddyDisplay extends Display {
    HasState<Boolean> getAcceptState();

    HasClickHandlers getCancel();

    HasChangeHandlers getEnterAction();

    Focusable getFirstFocusable();

    HasText getNickName();

    HasText getNickNameError();

    HasKeyDownHandlers getNickNameKeys();

    HasText getOldNickName();

    HasClickHandlers getSave();
}
