package com.calclab.hablar.signals.client.notifications;

import java.util.ArrayList;
import java.util.List;

import com.calclab.hablar.chat.client.ui.PairChatPresenter;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.events.UserMessageEvent;
import com.calclab.hablar.core.client.page.events.UserMessageHandler;
import com.calclab.hablar.rooms.client.room.RoomPresenter;
import com.calclab.hablar.roster.client.page.RosterPresenter;
import com.calclab.hablar.signals.client.SignalPreferences;

/**
 * A class which is responsible for maintaining a list of {@link HablarNotifier}
 * objects, storing that some are active, and sending messages to the active
 * ones.
 */
public class NotificationManager {

    /**
     * The list of active notifiers. This would have been cleaner to implement
     * this as a Map<HablarNotifier, Boolean> but since 99.9% of the calls to
     * this class will be to {@link NotificationManager#getActiveNotifiers()}
     * then doing it this way (two lists) should be more efficient.
     */
    private final ArrayList<HablarNotifier> activeNotifiers;

    /**
     * The list of the available notifiers.
     */
    private final ArrayList<HablarNotifier> notifiers;

    /**
     * The preferences.
     */
    private final SignalPreferences preferences;

    /**
     * Construct a new {@link NotificationManager} instance
     * 
     * @param eventBus
     *            the hablar event bus
     * @param preferences
     *            the preferences
     */
    public NotificationManager(final HablarEventBus eventBus, final SignalPreferences preferences) {

	this.preferences = preferences;
	eventBus.addHandler(UserMessageEvent.TYPE, new UserMessageHandler() {
	    @Override
	    public void onUserMessage(final UserMessageEvent event) {
		if (isVisibleMessage(event.getMessageType())) {
		    for (HablarNotifier notifier : activeNotifiers) {
			notifier.show(event.getUserMessage(), event.getMessageType());
		    }
		}
	    }
	});

	notifiers = new ArrayList<HablarNotifier>();
	activeNotifiers = new ArrayList<HablarNotifier>();
    }

    /**
     * This is to retain API compatibility.
     * 
     * @param eventBus
     *            the habler event bus
     * @param preferences
     *            the notification preferences object
     * @param notifier
     *            a notifier to add to the notification stack
     */
    public NotificationManager(final HablarEventBus eventBus, final SignalPreferences preferences,
	    final HablarNotifier notifier) {
	this(eventBus, preferences);

	addNotifier(notifier, true);
    }

    /**
     * Adds a notifier to the available notifier list.
     * 
     * @param notifier
     *            the {@link HablarNotifier} to add
     * @param active
     *            <code>true</code> to activate this notifier
     */
    public void addNotifier(HablarNotifier notifier, boolean active) {
	if (notifier == null) {
	    throw new NullPointerException("notifier cannot be null");
	}

	notifiers.add(notifier);

	if (active) {
	    activeNotifiers.add(notifier);
	}
    }

    /**
     * Returns the list of active notifiers set in theis manager
     * 
     * @return the list of notifiers
     */
    public List<HablarNotifier> getActiveNotifiers() {
	return activeNotifiers;
    }

    /**
     * Returns a list of all the notifiers installed in this manager.
     * 
     * @return the list of notifiers
     */
    public List<HablarNotifier> getAvailableNotifiers() {
	return notifiers;
    }

    /**
     * Returns whether a notifier is active or not.
     * 
     * @param notifier
     *            the notifier
     * @return <code>true</code> if this notifier is currently active,
     *         <code>false</code> otherwise
     */
    public boolean isNotifierActive(final HablarNotifier notifier) {
	return activeNotifiers.contains(notifier);
    }

    /**
     * Sets whether a notifier is active.
     * 
     * @param notifier
     *            the notifier
     * @param active
     *            <code>true</code> to enable this notifier, <code>false</code>
     *            otherwise
     */
    public void setNotifierActive(final HablarNotifier notifier, final boolean active) {
	if (notifier == null) {
	    throw new NullPointerException("notifier cannot be null");
	}

	if (!notifiers.contains(notifier)) {
	    throw new IllegalArgumentException("Unknown notifier");
	}

	if (active) {
	    if (!activeNotifiers.contains(notifier)) {
		activeNotifiers.add(notifier);
	    }
	} else {
	    activeNotifiers.remove(notifier);
	}
    }

    /**
     * Sets whether a notifier is active.
     * 
     * @param notifierId
     *            the notifier id
     * @param active
     *            <code>true</code> to enable this notifier, <code>false</code>
     *            otherwise
     */
    public void setNotifierActive(final String notifierId, final boolean active) {
	if (notifierId == null) {
	    throw new NullPointerException("notifierId cannot be null");
	}

	HablarNotifier notifier = getNotifierById(notifierId);

	if (notifier == null) {
	    throw new IndexOutOfBoundsException("Notifier " + notifierId + " not found");
	}

	setNotifierActive(notifier, active);
    }

    private HablarNotifier getNotifierById(final String id) {
	for (HablarNotifier notifier : notifiers) {
	    if (notifier.getId().equals(id)) {
		return notifier;
	    }
	}

	return null;
    }

    private boolean isVisibleMessage(final String messageType) {
	if (messageType.equals(PairChatPresenter.CHAT_MESSAGE) || messageType.equals(RoomPresenter.ROOM_MESSAGE)) {
	    return preferences.incomingMessages;
	} else if (messageType.equals(RosterPresenter.ROSTER_MESSAGE)) {
	    return preferences.rosterNotifications;
	}
	return false;
    }
}
