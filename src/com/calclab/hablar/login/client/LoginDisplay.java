package com.calclab.hablar.login.client;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface LoginDisplay extends Display {

    void addMessage(String message);

    HasClickHandlers getAction();

    HasText getActionText();

    HasText getPassword();

    HasText getUser();

    void setActionEnabled(boolean enabled);

}
