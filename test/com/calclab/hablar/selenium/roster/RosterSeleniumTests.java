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

    @Test
    public void shouldAddToGroup() {
	login();
	addSeleniumBuddy();
	addToGroup("testgroup");
	sleep(2000);
	removeFromGroup("testgroup");
	removeSeleniumBuddy();
	logout();
    }

}
