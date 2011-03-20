package com.calclab.hablar.user.client.storedpresence;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

import org.junit.Test;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.xtesting.services.TigaseXMLService;

public class StoredPresenceTest {

    @Test
    public void shouldComparePresences() {
	final StoredPresence presence1 = new StoredPresence("Sleeping...", Show.away);
	final StoredPresence presence2 = new StoredPresence(TigaseXMLService
		.toPacket("<hablarpresence><status>Sleeping...</status><show>away</show></hablarpresence>"));
	final StoredPresence presence3 = new StoredPresence("Reading...", Show.away);
	final StoredPresence presence4 = new StoredPresence("Sleeping...", Show.dnd);
	assertEquals(presence1, presence2);
	assertNotSame(presence1, presence3);
	assertNotSame(presence1, presence4);
    }
}
