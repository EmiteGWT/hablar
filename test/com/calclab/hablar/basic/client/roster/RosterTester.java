package com.calclab.hablar.basic.client.roster;

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

    /**
     * Add an item to this roster tester. No signals.
     * 
     * @param jid
     * @param name
     */
    public void addItem(XmppURI jid, String name) {
	super.storeItem(new RosterItem(jid, null, name, null));
    }

    @Override
    public void fireItemAdded(RosterItem item) {
	super.fireItemAdded(item);
    }

    @Override
    public void fireItemChanged(RosterItem item) {
	super.fireItemChanged(item);
    }

    @Override
    public void fireItemRemoved(RosterItem item) {
	super.fireItemRemoved(item);
    }

    @Override
    public void fireRosterReady(Collection<RosterItem> collection) {
	super.fireRosterReady(collection);
    }

    /**
     * Test if the given jid has been requested to add to roster
     * 
     * @param jid
     *            the jid
     * @return true if has been requested, false otherwise
     */
    public boolean hasRequestedToAdd(XmppURI jid) {
	for (Modification m : added) {
	    if (m.jid.equals(jid)) {
		return true;
	    }
	}
	return false;
    }

    @Override
    public void removeItem(XmppURI jid) {
	removed.add(jid);
    }

    @Override
    public void requestAddItem(XmppURI jid, String name, String... groups) {
	added.add(new Modification(jid, name, groups));
    }

    @Override
    public void updateItem(XmppURI jid, String name, String... groups) {
	updated.add(new Modification(jid, name, groups));
    }

}
