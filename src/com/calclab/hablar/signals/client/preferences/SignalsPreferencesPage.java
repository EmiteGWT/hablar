package com.calclab.hablar.signals.client.preferences;

import static com.calclab.hablar.signals.client.preferences.PreferencesConstants.*;

import java.util.HashMap;

import com.calclab.emite.xep.storage.client.IQResponse;
import com.calclab.emite.xep.storage.client.PrivateStorageManager;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.signals.client.SignalPreferences;
import com.calclab.hablar.user.client.EditorPage;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;

public class SignalsPreferencesPage extends PagePresenter<SignalsPreferencesDisplay> implements
	EditorPage<SignalsPreferencesDisplay> {

    public static final String TYPE = "SignalsPreferences";
    private final SignalPreferences preferences;
    private final StoredPreferencesManager storedPreferencesManager;
    private final Listener<IQResponse> savingListener;
    private boolean loading;

    public SignalsPreferencesPage(final HablarEventBus eventBus, final SignalPreferences preferences,
	    final SignalsPreferencesDisplay display) {
	super(TYPE, eventBus, display);
	this.preferences = preferences;
	storedPreferencesManager = new StoredPreferencesManager(Suco.get(PrivateStorageManager.class));
	savingListener = new Listener<IQResponse>() {
	    @Override
	    public void onEvent(final IQResponse parameter) {
		if (parameter.isError()) {
		    GWT.log("Error saving preferences");
		}
	    }
	};
    }

    @Override
    public void saveData() {
	if (!loading) {
	    preferences.titleSignals = display.getTitleSignals().getValue();
	    preferences.incomingNotifications = display.getIncomingNotifications().getValue();
	    preferences.rosterNotifications = display.getRosterNotifications().getValue();
	    final HashMap<String, String> newValues = new HashMap<String, String>();
	    newValues.put(TITLE_NOTIF, Boolean.toString(preferences.titleSignals));
	    newValues.put(INCOMING_NOTIF, Boolean.toString(preferences.incomingNotifications));
	    newValues.put(ROSTER_NOTIF, Boolean.toString(preferences.rosterNotifications));
	    storedPreferencesManager.put(newValues, savingListener);
	}
    }

    @Override
    public void showData() {
	setLoading(true);
	storedPreferencesManager.get(new Listener<IQResponse>() {
	    @Override
	    public void onEvent(final IQResponse parameter) {
		if (parameter.isSuccess()) {
		    final HashMap<String, String> prefs = StoredPreferences.parse(parameter).get();
		    preferences.titleSignals = Boolean.parseBoolean(prefs.get(TITLE_NOTIF));
		    preferences.incomingNotifications = Boolean.parseBoolean(prefs.get(INCOMING_NOTIF));
		    preferences.rosterNotifications = Boolean.parseBoolean(prefs.get(ROSTER_NOTIF));
		    display.getTitleSignals().setValue(preferences.titleSignals);
		    display.getIncomingNotifications().setValue(preferences.incomingNotifications);
		    display.getRosterNotifications().setValue(preferences.rosterNotifications);
		    setLoading(false);
		} else {
		    GWT.log("Error retrieving preferences");
		}
	    }
	});
    }

    @Override
    protected void onBeforeFocus() {
	setLoading(true);
    }

    private void setLoading(final boolean loading) {
	this.loading = loading;
	display.setLoadingVisible(loading);
	display.setFormVisible(!loading);
    }

}
