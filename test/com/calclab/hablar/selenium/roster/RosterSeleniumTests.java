package com.calclab.hablar.selenium.roster;

import org.testng.annotations.Test;

import com.calclab.hablar.selenium.HablarSeleniumTest;

public class RosterSeleniumTests extends HablarSeleniumTest {

    @Test
    public void shouldAddRosterItem() {
        login();
        roster.getHeader().click();
        search.getAction().click();
        search.getTerm().clear();
        search.getTerm().sendKeys("selenium\n");
        search.waitForResultMenu("selenium@localhost");
        search.getResultMenu("selenium@localhost").click();
        if (search.getRemoveBuddyAction().isDisplayed()) {
            search.getRemoveBuddyAction().click();
        }
        while (!search.getAddBuddyAction().isDisplayed()) {
            search.getResultMenu("selenium@localhost").click();
            sleep(1000);
        }
        search.getAddBuddyAction().click();
        logout();
    }

    @Test
    public void shouldRemoveRosterItem() {
        shouldAddRosterItem();
        login();
        roster.getHeader().click();
        roster.getItemMenu("", "selenium@localhost").click();
        roster.getRemoveBuddyAction().click();
        logout();
    }

}
