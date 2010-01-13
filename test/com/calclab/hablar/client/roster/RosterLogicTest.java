package com.calclab.hablar.client.roster;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Type;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.im.client.roster.SubscriptionState;
import com.calclab.hablar.client.chat.EmiteTester;

public class RosterLogicTest {

    private RosterView view;
    private EmiteTester tester;

    @Before
    public void before() {
	tester = new EmiteTester();
	view = mock(RosterView.class);
	new RosterLogic(view);
    }

    @Test
    public void shouldAddItemWhenRosterReceived() {
	Collection<RosterItem> items = new ArrayList<RosterItem>();
	RosterItem item = new RosterItem(XmppURI.uri("some@one"), SubscriptionState.from, "some", Type.subscribed);
	items.add(item);
	tester.roster.fireRosterReady(items);
	verify(view).addItem(item);
    }

    @Test
    public void shouldSetRosterInactiveWhenLoggedOut() {
	verify(view).setActive(false);
	tester.session.login(XmppURI.uri("someone"), null);
	tester.session.setReady();
	verify(view).setActive(true);
	tester.session.logout();
	verify(view, times(2)).setActive(false);
    }

}
