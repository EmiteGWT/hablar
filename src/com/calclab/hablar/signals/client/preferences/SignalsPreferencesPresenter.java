package com.calclab.hablar.signals.client.preferences;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.emite.xep.storage.client.IQResponse;
import com.calclab.emite.xep.storage.client.PrivateStorageManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.signals.client.I18nSignals;
import com.calclab.hablar.signals.client.SignalPreferences;
import com.calclab.hablar.user.client.EditorPage;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;

public class SignalsPreferencesPresenter extends PagePresenter<SignalsPreferencesDisplay> implements
	EditorPage<SignalsPreferencesDisplay> {

    public static final String TYPE = "SignalsPreferences";
    private final SignalPreferences preferences;
    private final PrivateStorageManager storageManager;
    private final Listener<IQResponse> savingListener;
    private boolean loaded;
    private StoredPreferences storedPreferences;

    public SignalsPreferencesPresenter(final HablarEventBus eventBus, final SignalPreferences preferences,
	    final SignalsPreferencesDisplay display) {
	super(TYPE, eventBus, display);
	this.preferences = preferences;
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

    }

    public boolean hasChanges(final StoredPreferences stored, final SignalPreferences preferences) {
	return preferences.incomingMessages != stored.getIncommingMessages()
		|| preferences.rosterNotifications != stored.getRosterNotifications()
		|| preferences.titleSignals != stored.getTitleSignals();
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

}
