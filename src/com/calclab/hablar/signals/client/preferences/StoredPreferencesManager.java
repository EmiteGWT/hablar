package com.calclab.hablar.signals.client.preferences;

import java.util.HashMap;

import com.calclab.emite.xep.storage.client.IQResponse;
import com.calclab.emite.xep.storage.client.PrivateStorageManager;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;

public class StoredPreferencesManager {
    private final PrivateStorageManager manager;

    public StoredPreferencesManager(final PrivateStorageManager manager) {
	this.manager = manager;
    }

    public void clearAll() {
	manager.store(StoredPreferences.empty, new Listener<IQResponse>() {
	    @Override
	    public void onEvent(final IQResponse response) {
		checkError(response);
	    }
	});
    }

    public void get(final Listener<IQResponse> listener) {
	manager.retrieve(StoredPreferences.empty, listener);
    }

    public void put(final HashMap<String, String> newValues, final Listener<IQResponse> listener) {
	manager.retrieve(StoredPreferences.empty, new Listener<IQResponse>() {
	    @Override
	    public void onEvent(final IQResponse response) {
		if (response.isSuccess()) {
		    final StoredPreferences currentPresences = StoredPreferences.parse(response);
		    boolean mustSave = false;
		    for (final String key : newValues.keySet()) {
			if (!currentPresences.put(key, newValues.get(key))) {
			    mustSave = true;
			}
		    }
		    if (mustSave) {
			manager.store(currentPresences, listener);
		    }
		} else {
		    checkError(response);
		}
	    }
	});
    }

    private void checkError(final IQResponse response) {
	if (response.isError()) {
	    GWT.log("Error clearing stored preferences");
	}
    }
}
