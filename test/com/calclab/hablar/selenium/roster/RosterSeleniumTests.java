package com.calclab.hablar.selenium.roster;

import org.testng.annotations.Test;

import com.calclab.hablar.selenium.HablarSeleniumTest;

public class RosterSeleniumTests extends HablarSeleniumTest {

    @Test
    public void shouldHaveRosterPage() {
	login("test1@localhost", "test1");
	roster.getHeader().click();
    }
}
