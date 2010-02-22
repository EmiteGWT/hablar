package com.calclab.hablar.signals.client.preferences;

import java.util.HashMap;

import com.calclab.emite.core.client.packet.IPacket;
import com.calclab.emite.core.client.packet.MatcherFactory;
import com.calclab.emite.xep.storage.client.IQResponse;
import com.calclab.emite.xep.storage.client.SimpleStorageData;

/**
 * SignalPreferences as StorageData
 */
public class StoredPreferences extends SimpleStorageData {

    public static final String HABLAR_PREFERENCES = "hablarpreferences";
    public static final String HABLAR_PREFERENCES_XMLNS = "hablar:preferences";
    public static final StoredPreferences empty = new StoredPreferences();

    public static final String TITLE_NOTIF = "title-notif";
    public static final String INCOMING_NOTIF = "incoming-notif";
    public static final String ROSTER_NOTIF = "roster-notif";

    public static StoredPreferences parse(final IQResponse response) {
	final StoredPreferences parsed = new StoredPreferences(response.getFirstChildInDeep(MatcherFactory
		.byNameAndXMLNS(HABLAR_PREFERENCES, HABLAR_PREFERENCES_XMLNS)));
	return parsed;
    }
    private HashMap<String, String> preferences;

    public StoredPreferences() {
	super(HABLAR_PREFERENCES, HABLAR_PREFERENCES_XMLNS);
	parsePresences();
    }

    public StoredPreferences(final IPacket delegate) {
	super(delegate);
	parsePresences();
    }

    public boolean getIncommingMessages() {
	return parseBoolean(preferences.get(INCOMING_NOTIF));
    }

    public boolean getRosterNotifications() {
	return parseBoolean(preferences.get(ROSTER_NOTIF));
    }

    public boolean getTitleSignals() {
	return parseBoolean(preferences.get(TITLE_NOTIF));
    }

    public void setIncomingMessages(final boolean incomingMessages) {
	put(INCOMING_NOTIF, Boolean.toString(incomingMessages));
    }

    public void setRosterNotifications(final boolean rosterNotifications) {
	put(ROSTER_NOTIF, Boolean.toString(rosterNotifications));
    }

    public void setTitleSignals(final boolean titleSignals) {
	put(TITLE_NOTIF, Boolean.toString(titleSignals));
    }

    /**
     * Return true always except when the given string is "false"
     * 
     * @param string
     *            the string to be parsed
     * @return true always except when the given string is "false"
     */
    private boolean parseBoolean(final String string) {
	return !"false".equals(string);
    }

    private void parsePresences() {
	if (preferences == null) {
	    preferences = new HashMap<String, String>();
	    for (final IPacket child : getChildren()) {
		preferences.put(child.getName(), child.getText());
	    }
	}
    }

    private void put(final String key, final String value) {
	setTextToChild(key, value);
	preferences.put(key, value);
    }
}
