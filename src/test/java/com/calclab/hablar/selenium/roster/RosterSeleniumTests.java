package com.calclab.hablar.selenium.roster;

import org.testng.annotations.Test;

import com.calclab.hablar.selenium.HablarSeleniumTest;

public class RosterSeleniumTests extends HablarSeleniumTest {

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
	final String newGroupName = "testgroup" + getTempString();
	addToGroup(newGroupName);
	sleep(2000);
	removeFromGroup(newGroupName);
	removeSeleniumBuddy();
	logout();
    }

}
