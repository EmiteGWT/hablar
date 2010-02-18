package com.calclab.hablar.user.client.storedpresence;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.xep.storage.client.IQResponse;
import com.calclab.emite.xtesting.services.TigaseXMLService;

public class StoredPresencesTest {

    public static String sample = "<hablarpresences xmlns=\"hablar:presence\">"
	    + "<hablarpresence><status>Sleeping...</status><show>away</show></hablarpresence>"
	    + "<hablarpresence><status>Cooking...</status><show>xa</show></hablarpresence></hablarpresences>";

    private final String packet = "<iq to=\"test1@localhost/1266328938509\" id=\"priv_1\" type=\"result\"><query xmlns=\"jabber:iq:private\"><hablarpresences xmlns=\"hablar:presences\"><hablarpresence><status>save1</status><show>dnd</show></hablarpresence></hablarpresences></query></iq>";
    private final String empty = "<iq to=\"test1@localhost/1266332042249\" id=\"priv_4\" type=\"result\"><query xmlns=\"jabber:iq:private\"><hablarpresences xmlns=\"hablar:presences\" /></query></iq>";

    @Test
    public void shouldAddtoEmptyResult() {
	final StoredPresences parsed = StoredPresences.parse(new IQResponse(TigaseXMLService.toPacket(empty)));
	parsed.add(new StoredPresence("Sleeping...", Show.away));
	assertEquals(1, parsed.get().size());
    }

    @Test
    public void shouldCheckContains() {
	final StoredPresences presences = new StoredPresences();
	presences.add(new StoredPresence("Sleeping...", Show.away));
	presences.add(new StoredPresence("Sleeping...", Show.away));
	presences.add(new StoredPresence("Cooking...", Show.xa));
	presences.add(new StoredPresence("Cooking...", Show.xa));
	presences.add(new StoredPresence("Cooking...", Show.xa));
	assertEquals(2, presences.get().size());
    }

    @Test
    public void shouldParse() {
	StoredPresences parsed = StoredPresences.parse(new IQResponse(TigaseXMLService.toPacket(packet)));
	assertEquals(1, parsed.get().size());
	parsed = StoredPresences.parse(new IQResponse(TigaseXMLService.toPacket(empty)));
	assertEquals(0, parsed.get().size());
    }

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
