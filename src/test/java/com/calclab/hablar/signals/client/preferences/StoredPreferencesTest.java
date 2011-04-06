package com.calclab.hablar.signals.client.preferences;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.calclab.emite.xep.storage.client.IQResponse;
import com.calclab.emite.xtesting.services.TigaseXMLService;

public class StoredPreferencesTest {
    public static String sample = "<hablarpreferences xmlns=\"hablar:preferences\">"
	    + "<title-notif>true</title_notif><incoming-notif>false</incoming-notif>"
	    + "<roster-notif>true</roster-notif>"
	    + "<notifier-notifieron-enabled>true</notifier-notifieron-enabled>"
	    + "<notifier-notifieroff-enabled>false</notifier-notifieroff-enabled>"
	    + "</hablarpreferences>";
    private final String packet = "<iq to=\"test1@localhost/1266328938509\" id=\"priv_1\" type=\"result\"><query xmlns=\"jabber:iq:private\">"
	    + sample + "</query></iq>";

    @Test
    public void shouldCreatePref() {
	final StoredPreferences preferences = new StoredPreferences(TigaseXMLService.toPacket(sample));
	shouldHaveCorrentPreferences(preferences);
    }

    @Test
    public void shouldParsePacket() {
	final StoredPreferences preferences = StoredPreferences
		.parse(new IQResponse(TigaseXMLService.toPacket(packet)));
	shouldHaveCorrentPreferences(preferences);
    }
    
    @Test
    public void testNotifierEnabled() {
	final StoredPreferences preferences = new StoredPreferences(TigaseXMLService.toPacket(sample));

	assertTrue("Enabled notifier not correctly identified", preferences.isNotifierEnabled("notifieron"));
    }
    
    @Test
    public void testNotifierDisabled() {
	final StoredPreferences preferences = new StoredPreferences(TigaseXMLService.toPacket(sample));

	assertFalse("Disabled notifier not correctly identified", preferences.isNotifierEnabled("notifieroff"));
    }
    
    @Test
    public void testNotifierNotSet() {
	final StoredPreferences preferences = new StoredPreferences(TigaseXMLService.toPacket(sample));

	assertNull("Unset notifier not correctly identified", preferences.isNotifierEnabled("notifiernull"));
    }

    private void shouldHaveCorrentPreferences(final StoredPreferences preferences) {
	assertTrue(preferences.getTitleSignals());
	assertFalse(preferences.getIncommingMessages());
	assertTrue(preferences.getRosterNotifications());
    }

}
