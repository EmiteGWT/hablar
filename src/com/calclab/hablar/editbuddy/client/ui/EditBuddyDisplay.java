package com.calclab.hablar.editbuddy.client.ui;

import com.calclab.hablar.basic.client.ui.Display;
import com.google.gwt.event.dom.client.HasChangeHandlers;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.Focusable;
import com.google.gwt.user.client.ui.HasText;

public interface EditBuddyDisplay extends Display {
    HasClickHandlers getCancel();

    HasChangeHandlers getEnterAction();

    Focusable getFirstFocusable();

    HasText getNewNickName();

    HasText getOldNickName();

    HasClickHandlers getSave();
}
