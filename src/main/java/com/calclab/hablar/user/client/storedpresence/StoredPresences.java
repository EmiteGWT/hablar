package com.calclab.hablar.user.client.storedpresence;

import java.util.ArrayList;
import java.util.List;

import com.calclab.emite.core.client.packet.IPacket;
import com.calclab.emite.core.client.packet.MatcherFactory;
import com.calclab.emite.xep.storage.client.IQResponse;
import com.calclab.emite.xep.storage.client.SimpleStorageData;

public class StoredPresences extends SimpleStorageData {

    public static final String HABLAR_PRESENCES = "hablarpresences";
    public static final String HABLAR_PRESENCE = "hablarpresence";
    public static final String HABLAR_PRESENCES_XMLNS = "hablar:presences";
    public static final StoredPresences empty = new StoredPresences();

    public static StoredPresences parse(final IQResponse response) {
	final StoredPresences parsed = new StoredPresences();
	for (final IPacket packet : (List<? extends IPacket>) response.getFirstChildInDeep(
		MatcherFactory.byNameAndXMLNS(HABLAR_PRESENCES, HABLAR_PRESENCES_XMLNS)).getChildren(
		MatcherFactory.byName(HABLAR_PRESENCE))) {
	    parsed.add(new StoredPresence(packet));
	}
	return parsed;
    }

    private ArrayList<StoredPresence> presences;

    public StoredPresences() {
	super(HABLAR_PRESENCES, HABLAR_PRESENCES_XMLNS);
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
	    for (final IPacket child : getChildren(MatcherFactory.byName(HABLAR_PRESENCE))) {
		presences.add(new StoredPresence(child));
	    }
	}
    }

}
