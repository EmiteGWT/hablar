package com.calclab.hablar.user.client.storedpresence;

import com.calclab.emite.core.client.packet.DelegatedPacket;
import com.calclab.emite.core.client.packet.IPacket;
import com.calclab.emite.core.client.packet.Packet;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;

public class StoredPresence extends DelegatedPacket {

    private static final String STATUS = "status";
    private static final String SHOW = "show";

    public StoredPresence() {
	super(new Packet(StoredPresences.STORED_PRESENCE));
    }

    public StoredPresence(final IPacket packet) {
	super(packet);
    }

    public StoredPresence(final String status, final Show show) {
	this();
	setStatus(status);
	setShow(show);
    }

    public Show getShow() {
	// Duplicate code with Presence.java
	final String value = getFirstChild(SHOW).getText();
	try {
	    return value != null ? Show.valueOf(value) : Show.notSpecified;
	} catch (final IllegalArgumentException e) {
	    return Show.unknown;
	}
    }

    public String getStatus() {
	return getFirstChild(STATUS).getText();
    }

    public void setShow(final Show show) {
	setTextToChild(SHOW, show.toString());
    }

    public void setStatus(final String status) {
	setTextToChild(STATUS, status);
    }
}
