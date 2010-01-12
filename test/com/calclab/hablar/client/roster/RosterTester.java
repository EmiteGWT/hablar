package com.calclab.hablar.client.roster;

import java.util.ArrayList;
import java.util.Collection;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.roster.AbstractRoster;
import com.calclab.emite.im.client.roster.Roster;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.suco.client.ioc.Container;
import com.calclab.suco.client.ioc.Provider;
import com.calclab.suco.client.ioc.decorator.NoDecoration;

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

    /**
     * Changes the real Roster to a instance of RosterTester
     * 
     * @param container
     *            the container to replace in
     * @return the RosterTester instance
     */
    public static RosterTester install(Container container) {
	final RosterTester instance = new RosterTester();
	container.removeProvider(Roster.class);
	container.registerProvider(NoDecoration.instance, Roster.class, new Provider<Roster>() {
	    @Override
	    public Roster get() {
		return instance;
	    }
	});
	return instance;
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
