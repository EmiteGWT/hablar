package com.calclab.hablar.chat.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface ChatMessages extends Messages {
    @DefaultMessage("User {0} says «{1}»")
    String newChatFrom(String userName, String msg);

    @DefaultMessage("{0} is active")
    String stateActive(String userName);

    @DefaultMessage("{0} is available")
    String stateAvailable(String userName);

    @DefaultMessage("{0} is writing...")
    String stateComposing(String userName);

    @DefaultMessage("{0} has gone")
    String stateGone(String userName);

    @DefaultMessage("{0} is inactive")
    String stateInactive(String userName);

    @DefaultMessage("User {0} is offline!")
    String stateOffline(String userName);

    @DefaultMessage("{0} is paused")
    String statePause(String userName);

    @DefaultMessage("Invia")
    String sendAction();
}
