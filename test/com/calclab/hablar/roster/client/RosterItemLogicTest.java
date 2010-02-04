package com.calclab.hablar.roster.client;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.im.client.roster.SubscriptionState;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.roster.client.RosterItemDisplay;
import com.calclab.hablar.roster.client.RosterItemPresenter;

public class RosterItemLogicTest {

    private RosterItemPresenter presenter;
    private RosterItemDisplay display;
    private RosterItem item;

    @Before
    public void before() {
	display = mock(RosterItemDisplay.class);
	presenter = new RosterItemPresenter(display);
	item = new RosterItem(XmppURI.uri("test1@localhost"), SubscriptionState.both, "test1", null);
    }

    @Test
    public void shouldSetAvailable() {
	item.setAvailable(true);
	presenter.setItem(item);
	String iconStyle = HablarIcons.get(HablarIcons.IconType.buddyOn);
	verify(display).setIcon(iconStyle);
    }

    @Test
    public void shouldSetOffline() {
    }

    @Test
    public void shouldSetProperties() {
    }
}
