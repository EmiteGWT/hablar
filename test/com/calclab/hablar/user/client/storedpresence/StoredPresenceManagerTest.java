package com.calclab.hablar.user.client.storedpresence;

import org.junit.Before;
import org.junit.Test;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.xep.storage.client.IQResponse;
import com.calclab.emite.xep.storage.client.PrivateStorageManager;
import com.calclab.emite.xtesting.XmppSessionTester;
import com.calclab.suco.testing.events.MockedListener;

public class StoredPresenceManagerTest {

	private static final String SUCCESS_RESULT = "<iq type=\"result\" from=\"test@domain/here\" to=\"test@domain/here\" id=\"1001\"/>";
	private static final String RETRIEVED = "<iq type=\"result\" from=\"test@domain/here\" to=\"test@domain/here\" id=\"1001\">"
			+ "<query xmlns=\"jabber:iq:private\">"
			+ StoredPresencesTest.sample + "</query></iq>";
	private StoredPresenceManager manager;
	private XmppSessionTester session;

	@Before
	public void before() {
		session = new XmppSessionTester("test@domain");
		manager = new StoredPresenceManager(new PrivateStorageManager(session));
	}

	@Test
	public void shouldAddOnce() {
		final MockedListener<IQResponse> listener = new MockedListener<IQResponse>();
		manager.add(new StoredPresence("Sleeping...", Show.away), listener);
		session.answer(SUCCESS_RESULT);
		manager.add(new StoredPresence("Sleeping...", Show.away), listener);
		manager.get(listener);
		session.answer(RETRIEVED);
		listener.isCalledOnce();
	}
}
