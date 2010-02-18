package com.calclab.hablar.signals.client.preferences;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

import com.calclab.emite.xep.storage.client.IQResponse;
import com.calclab.emite.xtesting.services.TigaseXMLService;

public class StoredPreferencesTest {
    public static String sample = "<hablarpreferences xmlns=\"hablar:preferences\">"
	    + "<title-notif>true</title_notif><incoming-notif>false</incoming-notif>"
	    + "<roster-notif>true</roster-notif></hablarpreferences>";
    private final String packet = "<iq to=\"test1@localhost/1266328938509\" id=\"priv_1\" type=\"result\"><query xmlns=\"jabber:iq:private\">"
	    + sample + "</query></iq>";

    @Test
    public void shouldCreatePref() {
	final StoredPreferences presences = new StoredPreferences(TigaseXMLService.toPacket(sample));
	checkPreferences(presences);
    }

    @Test
    public void shouldParseIq() {
	final StoredPreferences presences = StoredPreferences.parse(new IQResponse(TigaseXMLService.toPacket(packet)));
	checkPreferences(presences);
    }

    private void checkPreferences(final StoredPreferences presences) {
	final HashMap<String, String> prefs = presences.get();
	assertEquals(3, prefs.keySet().size());
	assertEquals("true", prefs.get(PreferencesConstants.TITLE_NOTIF));
	assertEquals("false", prefs.get(PreferencesConstants.INCOMING_NOTIF));
	assertEquals("true", prefs.get(PreferencesConstants.ROSTER_NOTIF));
	assertEquals(null, prefs.get("anything"));
    }
}
