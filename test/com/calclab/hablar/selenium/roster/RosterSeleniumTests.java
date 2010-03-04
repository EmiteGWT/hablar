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
	mustCloseFinally = false;
	login();
	addSeleniumBuddy();
	roster.getItemMenu("", "selenium@localhost").click();
	roster.getAddToGroupAction().click();
	roster.getAddToGroupNewGroupName().sendKeys("testgroup");
	roster.getAddToGroupAccept().click();
	sleep(1000);
	roster.getGroup("testgroup").click();
	roster.getItemMenu("testgroup", "selenium@localhost").click();
	roster.getRemoveFromGroupAction().click();
	removeSeleniumBuddy();
	logout();
    }
}
