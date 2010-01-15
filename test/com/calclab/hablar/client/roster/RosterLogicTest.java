package com.calclab.hablar.client.roster;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.client.chat.EmiteTester;

public class RosterLogicTest {

    private RosterView view;
    private EmiteTester tester;

    @Before
    public void before() {
	tester = new EmiteTester();
	view = mock(RosterView.class);
	when(view.createItemView()).thenAnswer(new Answer<RosterItemView>() {
	    @Override
	    public RosterItemView answer(InvocationOnMock invocation) throws Throwable {
		RosterItemIcons icons = mock(RosterItemIcons.class);
		RosterItemView itemView = mock(RosterItemView.class);
		when(itemView.getIconStyles()).thenReturn(icons);
		return itemView;
	    }
	});
	new RosterLogic(view);
    }

    @Test
    public void shouldActivateViewWhenItemAddedToRoster() {
	RosterItem item = newItem("someone");
	tester.roster.fireItemAdded(item);
	verify(view).createItemView();
	verify(view).setStatusMessage(anyString());
    }

    @Test
    public void shouldAddItemWhenRosterReceived() {
	Collection<RosterItem> items = new ArrayList<RosterItem>();
	RosterItem item = newItem("someone");
	items.add(item);
	tester.roster.fireRosterReady(items);
	verify(view).createItemView();
    }

    @Test
    public void shouldRemoveItems() {
	RosterItem item = newItem("friend");
	tester.roster.fireItemAdded(item);
	verify(view).createItemView();
	tester.roster.fireItemRemoved(item);
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

    private RosterItem newItem(String name) {
	return new RosterItem(XmppURI.uri(name + "@host"), null, name, null);
    }

}
