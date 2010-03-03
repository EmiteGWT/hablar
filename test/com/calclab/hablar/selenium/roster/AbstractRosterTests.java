package com.calclab.hablar.selenium.roster;

import com.calclab.hablar.selenium.HablarSeleniumTest;

public class AbstractRosterTests extends HablarSeleniumTest {
    protected void addBuddy(final String searchTerm, final String jid) {
	login();
	roster.getHeader().click();
	search.getAction().click();
	search.getTerm().clear();
	search.getTerm().sendKeys(searchTerm + "\n");
	search.waitForResultMenu(jid);
	search.getResultMenu(jid).click();
	if (search.getRemoveBuddyAction().isDisplayed()) {
	    search.getRemoveBuddyAction().click();
	}
	while (!search.getAddBuddyAction().isDisplayed()) {
	    search.getResultMenu(jid).click();
	    sleep(1000);
	}
	search.getAddBuddyAction().click();
	logout();
    }

    protected void addSeleniumBuddy() {
	addBuddy("selenium", "selenium@localhost");
    }

    protected void removeBuddy(final String groupId, final String jid) {
	login();
	roster.getHeader().click();
	roster.getItemMenu(groupId, jid).click();
	roster.getRemoveBuddyAction().click();
	logout();
    }

    protected void removeSeleniumBuddy() {
	removeBuddy("", "selenium@localhost");
    }

}
