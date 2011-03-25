package com.calclab.hablar.roster.client.page;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.calclab.emite.core.client.events.ChangedEvent.ChangeTypes;
import com.calclab.emite.im.client.roster.RosterGroup;
import com.calclab.emite.im.client.roster.events.RosterGroupChangedEvent;
import com.calclab.emite.xtesting.XmppSessionTester;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.roster.client.RosterConfig;
import com.calclab.hablar.roster.client.groups.RosterGroupPresenter;
import com.calclab.hablar.testing.EmiteTester;
import com.calclab.hablar.testing.HablarTester;
import com.calclab.hablar.testing.RosterTester;

public class RosterPresenterTests {

    private HablarEventBus hablarEventBus;
    private RosterDisplay display;
    private RosterTester roster;
    private XmppSessionTester session;

    @Before
    public void setup() {
	final EmiteTester emite = new EmiteTester();
	session = emite.session;
	roster = emite.roster;
	final HablarTester tester = new HablarTester();
	hablarEventBus = tester.eventBus;
	display = tester.newDisplay(RosterDisplay.class);
	final RosterConfig rosterConfig = new RosterConfig();
	rosterConfig.oneClickChat = true;
	new RosterPresenter(session, emite.xmppRoster, emite.chatManager, hablarEventBus, display, rosterConfig);
    }

    @Test
    public void shouldCreateGroupIfItemAddedInNewGroup() {

	final RosterGroup group = new RosterGroup("a new group");
	session.getEventBus().fireEvent(new RosterGroupChangedEvent(ChangeTypes.added, group));

	verify(display, times(1))
		.addGroup((RosterGroupPresenter) anyObject(), (Menu<RosterGroupPresenter>) anyObject());
    }

    @Test
    public void shouldCreateGroupIfItemChangedIntoANewGroup() {
	// roster.fireRosterReady();
	// verify(display, times(3)).addGroup((RosterGroupPresenter)
	// anyObject(),
	// (Menu<RosterGroupPresenter>) anyObject());
	// final RosterItem item = roster.newItem("jid2", "name", "group2",
	// "group3");
	// roster.storeItem(item);
	// roster.fireItemChanged(item);
	// verify(display, times(4)).addGroup((RosterGroupPresenter)
	// anyObject(),
	// (Menu<RosterGroupPresenter>) anyObject());
    }

    @Test
    public void shouldCreateRoster() {
	// roster.fireRosterReady();
	// verify(display, times(3)).addGroup((RosterGroupPresenter)
	// anyObject(),
	// (Menu<RosterGroupPresenter>) anyObject());
    }
}
