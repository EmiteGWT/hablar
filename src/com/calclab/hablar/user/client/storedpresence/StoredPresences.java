package com.calclab.hablar.user.client.storedpresence;

import java.util.ArrayList;
import java.util.List;

import com.calclab.emite.core.client.packet.IPacket;
import com.calclab.emite.core.client.packet.MatcherFactory;
import com.calclab.emite.xep.storage.client.SimpleStorageData;

public class StoredPresences extends SimpleStorageData {

    public static final String STORED_PRESENCES = "storedpresences";
    public static final String STORED_PRESENCE = "storedpresence";
    private static final String STORED_PRESENCE_XMLNS = "stored:presence";
    private ArrayList<StoredPresence> presences;

    public StoredPresences() {
	super(STORED_PRESENCES, STORED_PRESENCE_XMLNS);
    }

    public StoredPresences(final IPacket delegate) {
	super(delegate);
    }

    public void add(final StoredPresence storedPresence) {
	parsePresences();
	presences.add(storedPresence);
	addChild(STORED_PRESENCE);
    }

    public List<StoredPresence> get() {
	parsePresences();
	return presences;
    }

    private void parsePresences() {
	if (presences == null) {
	    presences = new ArrayList<StoredPresence>();
	    for (final IPacket child : getChildren(MatcherFactory.byName(STORED_PRESENCE))) {
		presences.add(new StoredPresence(child));
	    }
	}
    }

}
