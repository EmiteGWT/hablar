package com.calclab.hablar.client.roster;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.calclab.emite.core.client.EmiteCoreModule;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Type;
import com.calclab.emite.im.client.InstantMessagingModule;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.im.client.roster.SubscriptionState;
import com.calclab.suco.client.Suco;

public class RosterLogicTest {

    private RosterTester roster;
    private RosterView view;

    @Before
    public void before() {
	Suco.install(new EmiteCoreModule(), new InstantMessagingModule());
	roster = RosterTester.install(Suco.getComponents());
	view = mock(RosterView.class);
	new RosterLogic(view);
    }

    @Test
    public void should() {
	Collection<RosterItem> items = new ArrayList<RosterItem>();
	RosterItem item = new RosterItem(XmppURI.uri("some@one"), SubscriptionState.from, "some", Type.subscribed);
	items.add(item);
	roster.fireRosterReady(items);
	verify(view).addItem(item);
    }
}
