package com.calclab.hablar.user.client.storedpresence;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.xep.storage.client.IQResponse;
import com.calclab.emite.xep.storage.client.PrivateStorageManager;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;

public class StoredPresenceManager {
    private final PrivateStorageManager manager;

    public StoredPresenceManager(final PrivateStorageManager manager) {
	this.manager = manager;
    }

    public void add(final StoredPresence presence, final Listener<IQResponse> listener) {
	manager.retrieve(StoredPresences.empty, new Listener<IQResponse>() {
	    @Override
	    public void onEvent(final IQResponse response) {
		if (response.isSuccess()) {
		    final StoredPresences currentPresences = StoredPresences.parse(response);
		    if (currentPresences.add(presence)) {
			manager.store(currentPresences, listener);
		    }
		} else {
		    checkError(response);
		}
	    }
	});
    }

    public void add(final String status, final Show show, final Listener<IQResponse> listener) {
	add(new StoredPresence(status, show), listener);
    }

    public void clearAll() {
	manager.store(StoredPresences.empty, new Listener<IQResponse>() {
	    @Override
	    public void onEvent(final IQResponse response) {
		checkError(response);
	    }
	});
    }

    public void get(final Listener<IQResponse> listener) {
	manager.retrieve(StoredPresences.empty, listener);
    }

    private void checkError(final IQResponse response) {
	if (response.isError()) {
	    GWT.log("Error clearing stored presences", null);
	}
    }
}
