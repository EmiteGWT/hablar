package com.calclab.hablar.roster.client;

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

	public Modification(final XmppURI jid, final String name, final String[] groups) {
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
	added = new ArrayList<Modification>();
	updated = new ArrayList<Modification>();
	removed = new ArrayList<XmppURI>();
    }

    @Override
    public void fireItemAdded(final RosterItem item) {
	super.fireItemAdded(item);
    }

    @Override
    public void fireItemChanged(final RosterItem item) {
	super.fireItemChanged(item);
    }

    @Override
    public void fireItemRemoved(final RosterItem item) {
	super.fireItemRemoved(item);
    }

    @Override
    public void fireRosterReady(final Collection<RosterItem> collection) {
	super.fireRosterReady(collection);
    }

    /**
     * Test if the given jid has been requested to add to roster
     * 
     * @param jid
     *            the jid
     * @return true if has been requested, false otherwise
     */
    public boolean hasRequestedToAdd(final XmppURI jid) {
	for (final Modification m : added) {
	    if (m.jid.equals(jid)) {
		return true;
	    }
	}
	return false;
    }

    /**
     * Add an item to this roster tester. No signals.
     * 
     * @param jid
     * @param name
     * @return
     */
    public RosterItem newItem(final String jid, final String name, final String group) {
	final RosterItem item = new RosterItem(XmppURI.uri(jid), null, name, null);
	item.addToGroup(group);
	return item;
    }

    @Override
    public void removeItem(final XmppURI jid) {
	removed.add(jid);
    }

    @Override
    public void requestAddItem(final XmppURI jid, final String name, final String... groups) {
	added.add(new Modification(jid, name, groups));
    }

    @Override
    public void storeItem(final RosterItem item) {
	super.storeItem(item);
    }

    public void storeItem(final String jid, final String name, final String group) {
	storeItem(newItem(jid, name, group));
    }

    @Override
    public void updateItem(final XmppURI jid, final String name, final String... groups) {
	updated.add(new Modification(jid, name, groups));
    }

}
