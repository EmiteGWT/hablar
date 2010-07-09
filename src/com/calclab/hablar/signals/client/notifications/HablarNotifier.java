package com.calclab.hablar.signals.client.notifications;

/**
 * Interface for classes whcih provide the ability to display
 * notifications to the user 
 */
public interface HablarNotifier {

    /**
     * Display a message.
     * @param userMessage the message to show
     * @param messageType the message type string
     */
    void show(String userMessage, String messageType);

    /**
     * Returns an id for this type of notifier.
     * @return the id
     */
    String getId();
    
    /**
     * Returns the user readable display name for this notifier.
     * @return the display name
     */
    String getDisplayName();
}
