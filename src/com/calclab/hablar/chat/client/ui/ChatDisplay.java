package com.calclab.hablar.chat.client.ui;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public interface ChatDisplay extends Display {

    /**
     * Adds a message to this chat display
     * 
     * @param message
     */
    void addMessage(ChatMessage message);

    /**
     * Adds a widget to the action bar for this chat view
     * 
     * @param widget
     *            the widget to add
     */
    void addToActions(Widget widget);

    void clearAndFocus();

    HasClickHandlers createAction(Action<?> action);

    void focusInput();

    HasClickHandlers getAction();

    HasText getBody();

    HasText getState();

    HasKeyDownHandlers getTextBox();

    void setControlsVisible(boolean visible);

    /**
     * Ensure each chat display has a unique id
     */
    void setId(String id);

    void setStatusVisible(boolean visible);

}
