package com.calclab.hablar.selenium.roster;

import org.testng.annotations.Test;

import com.calclab.hablar.selenium.HablarSeleniumTest;

public class RosterSeleniumTests extends HablarSeleniumTest {

    @Test
    public void shouldHaveRosterPage() {
	login();
	roster.getHeader().click();
	logout();
    }

}
