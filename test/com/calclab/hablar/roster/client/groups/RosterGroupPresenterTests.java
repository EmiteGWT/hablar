package com.calclab.hablar.roster.client.groups;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.calclab.hablar.core.client.ui.menu.Menu;
import com.calclab.hablar.roster.client.RosterTester;
import com.calclab.hablar.testing.EmiteTester;
import com.calclab.hablar.testing.HablarTester;

public class RosterGroupPresenterTests {

    private RosterTester roster;
    private RosterGroupDisplay display;
    private Menu<RosterItemPresenter> itemMenu;

    @Before
    public void setup() {
	final EmiteTester emite = new EmiteTester();
	roster = emite.roster;
	final HablarTester hablar = new HablarTester();
	itemMenu = hablar.newMenu();
	final RosterItemDisplay itemDisplay = hablar.newDisplay(RosterItemDisplay.class);
	display = hablar.newDisplay(RosterGroupDisplay.class);
	when(display.newRosterItemDisplay()).thenReturn(itemDisplay);
	roster.storeItem("test1", "name1", "mygroup");
	roster.storeItem("test2", "name2", "mygroup");
	roster.storeItem("test3", "name3", "mygroup");
	new RosterGroupPresenter("mygroup", itemMenu, display);
    }

    @Test
    public void shouldAddWhenItemAdded() {
	roster.fireItemAdded(roster.newItem("test4", "name4", "mygroup"));
	verify(display, times(4)).add((RosterItemDisplay) anyObject());
    }

    @Test
    public void shouldRemoveWhenItemRemoved() {
	roster.fireItemRemoved(roster.newItem("test1", "name1", "mygroup"));
	verify(display).remove((RosterItemDisplay) anyObject());
    }

    @Test
    public void shouldRemoveWhenItemUpdated() {
	roster.fireItemChanged(roster.newItem("test1", "name1", "othergroup"));
	verify(display).remove((RosterItemDisplay) anyObject());
    }
}
