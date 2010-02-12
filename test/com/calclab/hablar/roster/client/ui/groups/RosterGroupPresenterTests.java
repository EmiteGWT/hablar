package com.calclab.hablar.roster.client.ui.groups;

import org.junit.Before;
import org.junit.Test;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.testing.EmiteTester;
import com.calclab.hablar.testing.HablarTester;

public class RosterGroupPresenterTests {

    @Before
    public void setup() {
	final EmiteTester emiteTester = new EmiteTester();
	final HablarTester hablarTester = new HablarTester();
	final RosterGroupDisplay display = hablarTester.newDisplay(RosterGroupDisplay.class);
	emiteTester.roster.addItem(XmppURI.uri("u1@d"), "u1", "group");
	emiteTester.roster.addItem(XmppURI.uri("u1@d"), "u1", "group");
	final Menu<RosterItem> menu = hablarTester.newMenu();
	// final RosterGroupPresenter presenter = new
	// RosterGroupPresenter("group", menu, display);
    }

    @Test
    public void shouldShowGroupItems() {

    }
}
