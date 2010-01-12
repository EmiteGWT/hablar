package com.calclab.hablar.client.roster;

import java.util.ArrayList;
import java.util.Collection;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.roster.AbstractRoster;
import com.calclab.emite.im.client.roster.RosterItem;

public class RosterTester extends AbstractRoster {
    public static class Modification {
	public final XmppURI jid;
	public final String name;
	public final String[] groups;

	public Modification(XmppURI jid, String name, String[] groups) {
	    this.jid = jid;
	    this.name = name;
	    this.groups = groups;
	}

    }

    private final ArrayList<Modification> added;
    private final ArrayList<Modification> updated;

    private final ArrayList<XmppURI> removed;

    /**
     * Generally you no need this.
     * 
     * @see RosterTester.install
     */
    public RosterTester() {
	this.added = new ArrayList<Modification>();
	this.updated = new ArrayList<Modification>();
	this.removed = new ArrayList<XmppURI>();
    }

    @Override
    public void addItem(XmppURI jid, String name, String... groups) {
	added.add(new Modification(jid, name, groups));
    }

    @Override
    public void fireRosterReady(Collection<RosterItem> collection) {
	super.fireRosterReady(collection);
    }

    @Override
    public void removeItem(XmppURI jid) {
	removed.add(jid);
    }

    @Override
    public void updateItem(XmppURI jid, String name, String... groups) {
	updated.add(new Modification(jid, name, groups));
    }
}
