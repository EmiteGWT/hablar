package com.calclab.hablar.signals.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface SignalMessages extends Messages {
    @DefaultMessage("Loading preferences...")
    String loadingPreferences();

    @DefaultMessage("Error saving preferences.")
    String saveError();

    @DefaultMessage("{0} conversations unread")
    @PluralText( { "one", "1 conversation unread" })
    String unreadChats(@PluralCount int users);

    @DefaultMessage("Please log in to retrieve preferences")
    String waitingToSession();

    @DefaultMessage("Notification preferences")
    String preferencesPageTitle();

    @DefaultMessage("Show unread conversations in title")
    String showUnreadConversations();

    @DefaultMessage("Show incoming message notifications")
    String showIncomingMessages();

    @DefaultMessage("Show roster notifications")
    String showRoster();
    
    @DefaultMessage("Notification within the chat window (jgrowl)")
    String jGrowlNotifierDisplayName();

    @DefaultMessage("Chat Notification")
    String browserPopupNotifierWindowTitle();

    @DefaultMessage("Browser Popup")
    String browserPopupNotifierDisplayName();
    
    @DefaultMessage("Notification Methods")
    String notificationMethods();
}
