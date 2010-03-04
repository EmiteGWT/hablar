package com.calclab.hablar.selenium.roster;

import org.testng.annotations.Test;

public class RosterSeleniumTests extends AbstractRosterTests {

    @Test
    public void shouldAddAndRemoveRosterItem() {
	login();
	addSeleniumBuddy();
	removeSeleniumBuddy();
	logout();
    }
}
