package com.calclab.hablar.client.roster;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.core.client.xmpp.stanzas.Presence.Show;
import com.calclab.emite.im.client.roster.RosterItem;

public class RosterItemLogicTest {

    private RosterItem item;
    private RosterItemView view;
    private RosterItemIcons icons;

    @Before
    public void before() {
	view = mock(RosterItemView.class);
	item = new RosterItem(XmppURI.uri("one"), null, "name", null);
	item.setAvailable(true);
	item.setStatus("the status");
	item.setShow(Show.dnd);
	icons = mock(RosterItemIcons.class);
	when(view.getIconStyles()).thenReturn(icons);
    }

    @Test
    public void shouldSetAvailable() {
	item.setAvailable(true);
	item.setShow(Show.notSpecified);
	RosterItemLogic.getIcon(item, icons);
	verify(icons).buddyIconOn();
    }

    @Test
    public void shouldSetProperties() {
	RosterItemLogic.setItem(item, view);
	verify(view).setName(item.getName());
	verify(view).setJID(item.getJID().toString());
	verify(view).setStatus(item.getStatus());
    }
}
