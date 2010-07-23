package com.calclab.hablar.rooms.client.existing;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;

public class ExistingRoom implements Comparable<ExistingRoom> {
    private final String name;
    private final XmppURI uri;

    public ExistingRoom(String name, XmppURI uri) {
	this.uri = uri;
	if (name == null) {
	    this.name = uri.toString();
	} else {
	    this.name = name;
	}
    }

    public String getName() {
	return name;
    }

    public XmppURI getUri() {
	return uri;
    }

    @Override
    public int compareTo(ExistingRoom o) {
	return name.compareTo(o.name);
    }

}