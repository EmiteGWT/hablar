package com.calclab.hablar.chat.client.ui;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface ChatDisplay extends Display {

    enum MessageType {
	incoming, sent
    }

    void clearAndFocus();

    HasClickHandlers getAction();

    HasText getBody();

    HasKeyDownHandlers getTextBox();

    void setControlsVisible(boolean visible);

    /**
     * Ensure each chat display has a unique id
     */
    void setId(String id);

    void showMessage(String name, String body, ChatDisplay.MessageType messageType);

    HasClickHandlers createAction(Action<ChatPage> action);

}
