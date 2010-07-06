package com.calclab.hablar.signals.client.notifications;

public interface HablarNotifier {

    void show(String userMessage, String messageType);

    String getId();
    
    String getDisplayName();
}
