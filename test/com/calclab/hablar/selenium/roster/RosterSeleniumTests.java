package com.calclab.hablar.selenium.roster;

import org.testng.annotations.Test;

public class RosterSeleniumTests extends AbstractRosterTests {

    @Test
    public void shouldAddRosterItem() {
	addSeleniumBuddy();
    }

    @Test
    public void shouldRemoveRosterItem() {
	addSeleniumBuddy();
	removeSeleniumBuddy();
    }
}
