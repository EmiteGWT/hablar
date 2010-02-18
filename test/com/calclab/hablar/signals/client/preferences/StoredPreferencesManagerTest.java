package com.calclab.hablar.signals.client.preferences;

import org.junit.Before;
import org.junit.Test;

import com.calclab.emite.xep.storage.client.IQResponse;
import com.calclab.emite.xep.storage.client.PrivateStorageManager;
import com.calclab.emite.xtesting.SessionTester;
import com.calclab.suco.testing.events.MockedListener;

public class StoredPreferencesManagerTest {

    private StoredPreferencesManager manager;
    private SessionTester session;
    private final String packet = "<iq type=\"get\"><query xmlns=\"jabber:iq:private\">"
	    + "<hablarpreferences xmlns=\"hablar:preferences\" /></query></iq>";

    @Before
    public void before() {
	session = new SessionTester("test@domain");
	manager = new StoredPreferencesManager(new PrivateStorageManager(session));
    }

    @Test
    public void testGet() {
	final MockedListener<IQResponse> listener = new MockedListener<IQResponse>();
	manager.get(listener);
	session.verifyIQSent(packet);
    }

}
