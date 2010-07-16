package com.calclab.hablar.roster.client.page;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.calclab.emite.im.client.roster.RosterItem;
import com.calclab.emite.xtesting.RosterTester;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.roster.client.RosterConfig;
import com.calclab.hablar.roster.client.groups.RosterGroupPresenter;
import com.calclab.hablar.testing.EmiteTester;
import com.calclab.hablar.testing.HablarTester;

public class RosterPresenterTests {

    private HablarEventBus eventBus;
    private RosterDisplay display;
    private RosterTester roster;

    @Before
    public void setup() {
	final EmiteTester emite = new EmiteTester();
	roster = emite.roster;
	final HablarTester tester = new HablarTester();
	eventBus = tester.eventBus;
	display = tester.newDisplay(RosterDisplay.class);
	final RosterConfig rosterConfig = new RosterConfig();
	rosterConfig.oneClickChat = true;
	new RosterPresenter(eventBus, display, rosterConfig);
	roster.storeItem("jid1", "name1", "group1");
	roster.storeItem("jid2", "name2", "group2");
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldCreateGroupIfItemAddedInNewGroup() {
	final RosterItem item = roster.newItem("jid3", "name3", "group3");
	roster.storeItem(item);
	roster.fireItemAdded(item);
	verify(display, times(4))
		.addGroup((RosterGroupPresenter) anyObject(), (Menu<RosterGroupPresenter>) anyObject());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldCreateGroupIfItemChangedIntoANewGroup() {
	roster.fireRosterReady();
	verify(display, times(3))
		.addGroup((RosterGroupPresenter) anyObject(), (Menu<RosterGroupPresenter>) anyObject());
	final RosterItem item = roster.newItem("jid2", "name", "group2", "group3");
	roster.storeItem(item);
	roster.fireItemChanged(item);
	verify(display, times(4))
		.addGroup((RosterGroupPresenter) anyObject(), (Menu<RosterGroupPresenter>) anyObject());
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldCreateRoster() {
	roster.fireRosterReady();
	verify(display, times(3))
		.addGroup((RosterGroupPresenter) anyObject(), (Menu<RosterGroupPresenter>) anyObject());
    }
}
