package com.calclab.hablar.signals.client.preferences;

import java.util.List;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.emite.xep.storage.client.IQResponse;
import com.calclab.emite.xep.storage.client.PrivateStorageManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.signals.client.I18nSignals;
import com.calclab.hablar.signals.client.SignalPreferences;
import com.calclab.hablar.signals.client.notifications.HablarNotifier;
import com.calclab.hablar.signals.client.notifications.NotificationManager;
import com.calclab.hablar.user.client.EditorPage;
import com.calclab.suco.client.Suco;
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

    public SignalsPreferencesPresenter(final HablarEventBus eventBus, final SignalPreferences preferences,
	    final SignalsPreferencesDisplay display, NotificationManager notificationManager) {
	super(TYPE, eventBus, display);
	this.preferences = preferences;
	this.notificationManager = notificationManager;
	storageManager = Suco.get(PrivateStorageManager.class);
	savingListener = new Listener<IQResponse>() {
	    @Override
	    public void onEvent(final IQResponse parameter) {
		if (parameter.isError()) {
		    setLoading(true, I18nSignals.t.saveError());
		}
	    }
	};

	setLoading(true, I18nSignals.t.waitingToSession());
	final Session session = Suco.get(Session.class);
	session.onStateChanged(new Listener<Session>() {
	    @Override
	    public void onEvent(final Session session) {
		if (session.getState() == State.ready) {
		    loadPreferences();
		}
	    }
	});
	if (session.getState() == State.ready) {
	    loadPreferences();
	}

	// Add the notifiers
	List<HablarNotifier> notifiers = notificationManager.getAvailableNotifiers();
	
	for(HablarNotifier notifier : notifiers) {
	    display.addNotifier(notifier.getId(), notifier.getDisplayName());
	}
	
	updateNotifierCheckboxStates();
    }

    public boolean hasChanges(final StoredPreferences stored, final SignalPreferences preferences) {
	if( preferences.incomingMessages != stored.getIncommingMessages()
		|| preferences.rosterNotifications != stored.getRosterNotifications()
		|| preferences.titleSignals != stored.getTitleSignals()) {
	    return true;
	}
	
	for(HablarNotifier notifier : notificationManager.getAvailableNotifiers()) {
	    if(notificationManager.isNotifierActive(notifier) != display.isNotifierSelected(notifier.getId())) {
		return true;
	    }
	}
	
	return false;
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
		
		for(HablarNotifier notifier : notificationManager.getAvailableNotifiers()) {
		    notifierSelected = display.isNotifierSelected(notifier.getId());
		    
		    storedPreferences.setNotifierEnabled(notifier.getId(), notifierSelected);
		    notificationManager.setNotifierActive(notifier, notifierSelected);
		}
		
		storageManager.store(storedPreferences, savingListener);
	    }
	}
    }

    @Override
    public void showData() {
	display.getTitleSignals().setValue(preferences.titleSignals);
	display.getIncomingNotifications().setValue(preferences.incomingMessages);
	display.getRosterNotifications().setValue(preferences.rosterNotifications);
	display.setLoadingVisible(!loaded);
	display.setFormVisible(loaded);
    }

    private void loadPreferences() {
	setLoading(true, I18nSignals.t.loadingPreferences());
	storageManager.retrieve(StoredPreferences.empty, new Listener<IQResponse>() {
	    @Override
	    public void onEvent(final IQResponse parameter) {
		if (parameter.isSuccess()) {
		    storedPreferences = StoredPreferences.parse(parameter);
		    preferences.titleSignals = storedPreferences.getTitleSignals();
		    preferences.incomingMessages = storedPreferences.getIncommingMessages();
		    preferences.rosterNotifications = storedPreferences.getRosterNotifications();
		    
		    // Update the NotifierManager with the enabled notifiers
		    for(HablarNotifier notifier : notificationManager.getAvailableNotifiers()) {
			Boolean enabled = storedPreferences.isNotifierEnabled(notifier.getId());
			if(enabled != null) {
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

    private void setLoading(final boolean isLoading, final String message) {
	loaded = !isLoading;
	display.setLoadingVisible(isLoading);
	if (isLoading && message != null) {
	    display.getLoading().setText(message);
	}
	display.setFormVisible(loaded);
    }
    
    private void updateNotifierCheckboxStates() {
	for(HablarNotifier notifier : notificationManager.getAvailableNotifiers()) {
	    display.setNotifierSelected(notifier.getId(), notificationManager.isNotifierActive(notifier));
	}
    }

}
