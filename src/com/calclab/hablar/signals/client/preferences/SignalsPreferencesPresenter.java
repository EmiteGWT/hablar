package com.calclab.hablar.signals.client.preferences;

import java.util.List;

import com.calclab.emite.core.client.events.StateChangedEvent;
import com.calclab.emite.core.client.events.StateChangedHandler;
import com.calclab.emite.core.client.xmpp.session.SessionStates;
import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.xep.storage.client.IQResponse;
import com.calclab.emite.xep.storage.client.PrivateStorageManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.signals.client.HablarSignals;
import com.calclab.hablar.signals.client.SignalPreferences;
import com.calclab.hablar.signals.client.notifications.HablarNotifier;
import com.calclab.hablar.signals.client.notifications.NotificationManager;
import com.calclab.hablar.user.client.EditorPage;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;

public class SignalsPreferencesPresenter extends PagePresenter<SignalsPreferencesDisplay> implements
	EditorPage<SignalsPreferencesDisplay> {

    public static final String TYPE = "SignalsPreferences";
    private final SignalPreferences preferences;
    private final PrivateStorageManager storageManager;
    private final NotificationManager notificationManager;
    private final Listener<IQResponse> savingListener;
    private boolean loaded;
    private StoredPreferences storedPreferences;

    public SignalsPreferencesPresenter(final XmppSession session, final PrivateStorageManager storageManager,
	    final HablarEventBus eventBus, final SignalPreferences preferences,
	    final SignalsPreferencesDisplay display, final NotificationManager notificationManager) {
	super(TYPE, eventBus, display);

	this.storageManager = storageManager;
	this.preferences = preferences;
	this.notificationManager = notificationManager;
	savingListener = new Listener<IQResponse>() {
	    @Override
	    public void onEvent(final IQResponse parameter) {
		if (parameter.isError()) {
		    setLoading(true, HablarSignals.signalMessages.saveError());
		}
	    }
	};

	setLoading(true, HablarSignals.signalMessages.waitingToSession());

	session.addSessionStateChangedHandler(true, new StateChangedHandler() {
	    @Override
	    public void onStateChanged(final StateChangedEvent event) {
		if (SessionStates.isReady(event.getState())) {
		    loadPreferences();
		}
	    }
	});

	// Add the notifiers
	final List<HablarNotifier> notifiers = notificationManager.getAvailableNotifiers();

	for (final HablarNotifier notifier : notifiers) {
	    display.addNotifier(notifier.getId(), notifier.getDisplayName());
	}

	updateNotifierCheckboxStates();
    }

    public boolean hasChanges(final StoredPreferences stored, final SignalPreferences preferences) {
	if ((preferences.incomingMessages != stored.getIncommingMessages())
		|| (preferences.rosterNotifications != stored.getRosterNotifications())
		|| (preferences.titleSignals != stored.getTitleSignals())) {
	    return true;
	}

	for (final HablarNotifier notifier : notificationManager.getAvailableNotifiers()) {
	    if (notificationManager.isNotifierActive(notifier) != display.isNotifierSelected(notifier.getId())) {
		return true;
	    }
	}

	return false;
    }

    private void loadPreferences() {
	setLoading(true, HablarSignals.signalMessages.loadingPreferences());
	storageManager.retrieve(StoredPreferences.empty, new Listener<IQResponse>() {
	    @Override
	    public void onEvent(final IQResponse parameter) {
		if (parameter.isSuccess()) {
		    storedPreferences = StoredPreferences.parse(parameter);
		    preferences.titleSignals = storedPreferences.getTitleSignals();
		    preferences.incomingMessages = storedPreferences.getIncommingMessages();
		    preferences.rosterNotifications = storedPreferences.getRosterNotifications();

		    // Update the NotifierManager with the enabled notifiers
		    for (final HablarNotifier notifier : notificationManager.getAvailableNotifiers()) {
			final Boolean enabled = storedPreferences.isNotifierEnabled(notifier.getId());
			if (enabled != null) {
			    notificationManager.setNotifierActive(notifier, enabled);
			}
		    }
		    updateNotifierCheckboxStates();
		    showData();
		    setLoading(false, null);
		} else {
		    GWT.log("PREFERENCES Error retrieving");
		}
	    }
	});
    }

    @Override
    public void saveData() {
	if (loaded) {
	    preferences.titleSignals = display.getTitleSignals().getValue();
	    preferences.incomingMessages = display.getIncomingNotifications().getValue();
	    preferences.rosterNotifications = display.getRosterNotifications().getValue();
	    if (hasChanges(storedPreferences, preferences)) {
		storedPreferences.setTitleSignals(preferences.titleSignals);
		storedPreferences.setIncomingMessages(preferences.incomingMessages);
		storedPreferences.setRosterNotifications(preferences.rosterNotifications);

		boolean notifierSelected;

		for (final HablarNotifier notifier : notificationManager.getAvailableNotifiers()) {
		    notifierSelected = display.isNotifierSelected(notifier.getId());

		    storedPreferences.setNotifierEnabled(notifier.getId(), notifierSelected);
		    notificationManager.setNotifierActive(notifier, notifierSelected);
		}

		storageManager.store(storedPreferences, savingListener);
	    }
	}
    }

    private void setLoading(final boolean isLoading, final String message) {
	loaded = !isLoading;
	display.setLoadingVisible(isLoading);
	if (isLoading && (message != null)) {
	    display.getLoading().setText(message);
	}
	display.setFormVisible(loaded);
    }

    @Override
    public void showData() {
	display.getTitleSignals().setValue(preferences.titleSignals);
	display.getIncomingNotifications().setValue(preferences.incomingMessages);
	display.getRosterNotifications().setValue(preferences.rosterNotifications);
	display.setLoadingVisible(!loaded);
	display.setFormVisible(loaded);
    }

    private void updateNotifierCheckboxStates() {
	for (final HablarNotifier notifier : notificationManager.getAvailableNotifiers()) {
	    display.setNotifierSelected(notifier.getId(), notificationManager.isNotifierActive(notifier));
	}
    }

}
