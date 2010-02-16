package com.calclab.hablar.user.client.storedpresence;

import java.util.ArrayList;
import java.util.List;

import com.calclab.emite.core.client.packet.IPacket;
import com.calclab.emite.core.client.packet.MatcherFactory;
import com.calclab.emite.xep.storage.client.IQResponse;
import com.calclab.emite.xep.storage.client.SimpleStorageData;

public class StoredPresences extends SimpleStorageData {

    public static final String STORED_PRESENCES = "storedpresences";
    public static final String STORED_PRESENCE = "storedpresence";
    public static final String STORED_PRESENCES_XMLNS = "stored:presence";
    public static final StoredPresences empty = new StoredPresences();

    public static StoredPresences parse(final IQResponse response) {
	final StoredPresences parsed = new StoredPresences();
	for (final IPacket packet : (List<? extends IPacket>) response.getFirstChildInDeep(
		MatcherFactory.byNameAndXMLNS(STORED_PRESENCES, STORED_PRESENCES_XMLNS)).getChildren(
		MatcherFactory.byName(STORED_PRESENCE))) {
	    parsed.add(new StoredPresence(packet));
	}
	return parsed;
    }

    private ArrayList<StoredPresence> presences;

    public StoredPresences() {
	super(STORED_PRESENCES, STORED_PRESENCES_XMLNS);
    }

    public StoredPresences(final IPacket delegate) {
	super(delegate);
    }

    public boolean add(final StoredPresence presence) {
	parsePresences();
	if (!presences.contains(presence)) {
	    presences.add(presence);
	    addChild(presence);
	    return true;
	}
	return false;
    }

    public boolean contains(final StoredPresence presence) {
	parsePresences();
	return presences.contains(presence);
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
