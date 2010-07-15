package com.calclab.hablar.roster.client;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.im.client.roster.SubscriptionState;
import com.calclab.hablar.core.client.ui.icon.OldHablarIcons;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.roster.client.groups.RosterItemDisplay;
import com.calclab.hablar.roster.client.groups.RosterItemPresenter;
import com.calclab.hablar.testing.HablarTester;

public class RosterItemLogicTest {

    private RosterItemPresenter presenter;
    private RosterItemDisplay display;
    private RosterItem item;

    @Before
    public void before() {
	final HablarTester tester = new HablarTester();
	display = tester.newDisplay(RosterItemDisplay.class);
	final Menu<RosterItemPresenter> menu = tester.newMenu();
	RosterConfig rosterConfig = new RosterConfig();
	rosterConfig.oneClickChat = true;
	presenter = new RosterItemPresenter("group", menu, display, rosterConfig);
	item = new RosterItem(XmppURI.uri("test1@localhost"), SubscriptionState.both, "test1", null);
    }

    @Test
    public void shouldSetAvailable() {
	item.setAvailable(true);
	presenter.setItem(item);
	verify(display).setIcon(OldHablarIcons.getBundle().buddyIconOn());
    }

    @Test
    public void shouldSetOffline() {
    }

    @Test
    public void shouldSetProperties() {
    }
}
