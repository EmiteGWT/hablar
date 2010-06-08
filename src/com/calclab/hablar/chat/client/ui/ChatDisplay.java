package com.calclab.hablar.chat.client.ui;

import java.util.ArrayList;

import com.calclab.hablar.core.client.mvp.Display;
import com.calclab.hablar.core.client.ui.menu.Action;
import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.dom.client.HasKeyDownHandlers;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.Widget;

public interface ChatDisplay extends Display {

    enum MessageType {
	incoming, sent, info, alert
    }

    /**
     * Add a complext (html) message to the chat panel
     * 
     * @param name
     * @param body
     * @param messageType
     * @return
     */
    ChatMessageDisplay addMessage(String name, Element body, ChatDisplay.MessageType messageType);

    /**
     * Add a simple (string) message to the chat panel
     * 
     * @param name
     * @param body
     * @param messageType
     * @return
     */
    ChatMessageDisplay addMessage(String name, String body, ChatDisplay.MessageType messageType);

    /**
     * Adds a widget to the action bar for this chat view
     * 
     * @param widget
     *            the widget to add
     */
    void addToActions(Widget widget);

    void clearAndFocus();

    HasClickHandlers createAction(Action<?> action);

    HasClickHandlers getAction();

    HasText getBody();

    /**
     * Return a list with all the messages of this chat panel
     * 
     * @return a list, never null but can be empty
     */
    ArrayList<ChatMessageDisplay> getMessages();

    HasText getState();

    HasKeyDownHandlers getTextBox();

    void setControlsVisible(boolean visible);

    /**
     * Ensure each chat display has a unique id
     */
    void setId(String id);

    void setStatusVisible(boolean visible);

}
