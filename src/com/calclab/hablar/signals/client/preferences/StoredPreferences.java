package com.calclab.hablar.signals.client.preferences;

import java.util.HashMap;

import com.calclab.emite.core.client.packet.IPacket;
import com.calclab.emite.core.client.packet.MatcherFactory;
import com.calclab.emite.xep.storage.client.IQResponse;
import com.calclab.emite.xep.storage.client.SimpleStorageData;

public class StoredPreferences extends SimpleStorageData {

    public static final String HABLAR_PREFERENCES = "hablarpreferences";
    public static final String HABLAR_PREFERENCES_XMLNS = "hablar:preferences";
    public static final StoredPreferences empty = new StoredPreferences();

    public static StoredPreferences parse(final IQResponse response) {
	final StoredPreferences parsed = new StoredPreferences(response.getFirstChildInDeep(MatcherFactory
		.byNameAndXMLNS(HABLAR_PREFERENCES, HABLAR_PREFERENCES_XMLNS)));
	return parsed;
    }
    private HashMap<String, String> preferences;

    public StoredPreferences() {
	super(HABLAR_PREFERENCES, HABLAR_PREFERENCES_XMLNS);
    }

    public StoredPreferences(final IPacket delegate) {
	super(delegate);
    }

    public boolean contains(final String key) {
	parsePresences();
	return preferences.containsKey(key);
    }

    public HashMap<String, String> get() {
	parsePresences();
	return preferences;
    }

    public boolean put(final String key, final String value) {
	parsePresences();
	setTextToChild(key, value);
	return value.equals(preferences.put(key, value));
    }

    private void parsePresences() {
	if (preferences == null) {
	    preferences = new HashMap<String, String>();
	    for (final IPacket child : getChildren()) {
		preferences.put(child.getName(), child.getText());
	    }
	}
    }
}
