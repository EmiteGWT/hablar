package com.calclab.hablar.console.client;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface ConsoleDisplay extends Display {

    void add(String message, String styleName, String sessionStyle);

    void clear();

    HasText getInputText();

    HasClickHandlers getClear();

    HasKeyDownHandlers getInput();

    void setInputCursorPos(int pos);

}
