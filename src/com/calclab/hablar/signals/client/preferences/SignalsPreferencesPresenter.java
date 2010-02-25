package com.calclab.hablar.signals.client.preferences;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.emite.xep.storage.client.IQResponse;
import com.calclab.emite.xep.storage.client.PrivateStorageManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
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
    private StoredPreferences currentPreferences;

    public SignalsPreferencesPresenter(final HablarEventBus eventBus, final SignalPreferences preferences,
	    final SignalsPreferencesDisplay display) {
	super(TYPE, eventBus, display);
	this.preferences = preferences;
	storageManager = Suco.get(PrivateStorageManager.class);
	savingListener = new Listener<IQResponse>() {
	    @Override
	    public void onEvent(final IQResponse parameter) {
		if (parameter.isError()) {
		    GWT.log("Error saving preferences");
		}
	    }
	};

	loaded = false;
	final Session session = Suco.get(Session.class);
	session.onStateChanged(new Listener<Session>() {
	    @Override
	    public void onEvent(final Session session) {
		if (session.getState() == State.ready) {
		    loadPreferences();
		}
	    }
	});
    }

    public boolean changed(final StoredPreferences stored, final SignalPreferences preferences) {
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
	    if (changed(currentPreferences, preferences)) {
		currentPreferences.setTitleSignals(preferences.titleSignals);
		currentPreferences.setIncomingMessages(preferences.incomingMessages);
		currentPreferences.setRosterNotifications(preferences.rosterNotifications);
		storageManager.store(currentPreferences, savingListener);
	    }
	}
    }

    @Override
    public void showData() {
	GWT.log("PREFERENCES SHOW: " + loaded);
	display.getTitleSignals().setValue(preferences.titleSignals);
	display.getIncomingNotifications().setValue(preferences.incomingMessages);
	display.getRosterNotifications().setValue(preferences.rosterNotifications);
	display.setLoadingVisible(!loaded);
	display.setFormVisible(loaded);
    }

    private void loadPreferences() {
	GWT.log("PREFERENCES loading");
	storageManager.retrieve(StoredPreferences.empty, new Listener<IQResponse>() {

	    @Override
	    public void onEvent(final IQResponse parameter) {
		if (parameter.isSuccess()) {
		    GWT.log("PREFERENCES retrieved");
		    loaded = true;
		    currentPreferences = StoredPreferences.parse(parameter);
		    preferences.titleSignals = currentPreferences.getTitleSignals();
		    preferences.incomingMessages = currentPreferences.getIncommingMessages();
		    preferences.rosterNotifications = currentPreferences.getRosterNotifications();
		    showData();
		} else {
		    GWT.log("PREFERENCES Error retrieving");
		}
	    }
	});
    }

}
