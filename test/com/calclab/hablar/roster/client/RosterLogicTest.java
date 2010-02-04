package com.calclab.hablar.roster.client;

import org.junit.Before;
import org.junit.Test;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.roster.RosterItem;

public class RosterLogicTest {

    @Before
    public void before() {
    }

    @Test
    public void shouldActivateViewWhenItemAddedToRoster() {
    }

    @Test
    public void shouldAddItemWhenRosterReceived() {
    }

    @Test
    public void shouldRemoveItems() {
    }

    @Test
    public void shouldSetRosterInactiveWhenLoggedOut() {
    }

    protected RosterItem newItem(final String name) {
	return new RosterItem(XmppURI.uri(name + "@host"), null, name, null);
    }

}
