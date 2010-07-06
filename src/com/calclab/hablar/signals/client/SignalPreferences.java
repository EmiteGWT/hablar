package com.calclab.hablar.signals.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.calclab.hablar.signals.client.notifications.HablarNotifier;

public class SignalPreferences {

    /**
     * Show unread conversations in title
     */
    public boolean titleSignals = true;

    /**
     * Show incoming messages (either in char or rooms) notifications
     */
    public boolean incomingMessages = true;

    /**
     * Show roster notifications
     */
    public boolean rosterNotifications = true;
}
