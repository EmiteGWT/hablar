package com.calclab.hablar.roster.client;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.im.client.roster.SubscriptionState;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.roster.client.ui.groups.RosterItemDisplay;
import com.calclab.hablar.roster.client.ui.groups.RosterItemPresenter;
import com.calclab.hablar.testing.HablarTester;

public class RosterItemLogicTest {

    private RosterItemPresenter presenter;
    private RosterItemDisplay display;
    private RosterItem item;

    @SuppressWarnings("unchecked")
    @Before
    public void before() {
	final HablarTester tester = new HablarTester();
	display = tester.newDisplay(RosterItemDisplay.class);
	final Menu<RosterItem> menu = tester.newMenu();
	presenter = new RosterItemPresenter(menu, display);
	item = new RosterItem(XmppURI.uri("test1@localhost"), SubscriptionState.both, "test1", null);
    }

    @Test
    public void shouldSetAvailable() {
	item.setAvailable(true);
	presenter.setItem(item);
	final String iconStyle = HablarIcons.get(HablarIcons.IconType.buddyOn);
	verify(display).setIcon(iconStyle);
    }

    @Test
    public void shouldSetOffline() {
    }

    @Test
    public void shouldSetProperties() {
    }
}
