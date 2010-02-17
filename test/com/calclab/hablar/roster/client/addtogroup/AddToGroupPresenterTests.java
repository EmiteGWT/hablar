package com.calclab.hablar.roster.client.addtogroup;

import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.roster.client.RosterTester;
import com.calclab.hablar.roster.client.addtogroup.AddToGroupDisplay.Action;
import com.calclab.hablar.testing.EmiteTester;
import com.calclab.hablar.testing.EventBusTester;
import com.calclab.hablar.testing.HablarTester;

public class AddToGroupPresenterTests {

    private RosterTester roster;
    private AddToGroupDisplay display;
    private AddToGroupPresenter presenter;

    @Before
    public void setup() {
	final EmiteTester emite = new EmiteTester();
	roster = emite.roster;
	final HablarTester tester = new HablarTester();
	final EventBusTester eventBus = tester.eventBus;
	display = tester.newDisplay(AddToGroupDisplay.class);
	presenter = new AddToGroupPresenter(eventBus, display);
    }

    @Test
    public void shouldAddCurrentGroupsWhenShow() {
	roster.setGroups("one", "two", "three");
	presenter.setVisibility(Visibility.focused);

	verify(display).clearGroupList();
	verify(display).addToGroupList("one");
	verify(display).addToGroupList("two");
	verify(display).addToGroupList("three");
	verify(display).setActiveAction(Action.addToExisting);
    }

    @Test
    public void shouldDisableAddToExistingIfNoRosterGroups() {
	roster.setGroups();
	presenter.setVisibility(Visibility.focused);
	verify(display).clearGroupList();
	verify(display).setActionEnabled(Action.addToExisting, false);
	verify(display).setActiveAction(Action.addToNew);
    }
}
