package com.calclab.hablar.core.client.pages;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface HeaderDisplay extends Display {
    void addIconStyle(String iconStyle);

    void addStyle(String styleName);

    HasClickHandlers getClose();

    HasText getHeaderTitle();

    HasClickHandlers getOpen();

    void removeIconStyle(String iconStyle);

    void removeStyle(String styleName);

    void setCloseIconVisible(boolean visible);
}
