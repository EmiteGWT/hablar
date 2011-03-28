package com.calclab.hablar.core.client.ui.prompt;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface ConfirmPageDisplay extends Display {

    HasClickHandlers getYes();

    HasClickHandlers getNo();

    HasText getMessage();

    void setPageTitle(String title);

}
