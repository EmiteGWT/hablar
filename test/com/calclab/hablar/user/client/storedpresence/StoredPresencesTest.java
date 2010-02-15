package com.calclab.hablar.user.client.storedpresence;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.xtesting.services.TigaseXMLService;

public class StoredPresencesTest {

    String sample = "<storedpresences xmlns=\"stored:presence\">"
	    + "<storedpresence><status>Sleeping...</status><show>away</show></storedpresence>"
	    + "<storedpresence><status>Cooking...</status><show>xa</show></storedpresence></storedpresences>";

    @Test
    public void shouldParseCorrectly() {
	final StoredPresences presences = new StoredPresences(TigaseXMLService.toPacket(sample));
	final List<StoredPresence> presenceList = presences.get();
	assertEquals(2, presenceList.size());
	final StoredPresence first = presenceList.get(0);
	final StoredPresence snd = presenceList.get(1);
	assertEquals("Sleeping...", first.getStatus());
	assertEquals("Cooking...", snd.getStatus());
	assertEquals(Show.away, first.getShow());
	assertEquals(Show.xa, snd.getShow());
    }

    @Test
    public void shouldSimpleAdd() {
	final StoredPresences presences = new StoredPresences();
	presences.add(new StoredPresence("Sleeping...", Show.away));
	presences.add(new StoredPresence("Cooking...", Show.xa));
	assertEquals(2, presences.get().size());
    }
}
