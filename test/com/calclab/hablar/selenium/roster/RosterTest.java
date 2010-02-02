package com.calclab.hablar.selenium.roster;

import org.testng.annotations.Test;

import com.calclab.hablar.selenium.HablarSeleniumTest;

public class RosterTest extends HablarSeleniumTest {

    @Test
    public void testRosteritemMenu() {
	login("test1");
	roster.getHeader().click();
	roster.getSearchIcon().click();
	search.getTerm().clear();
	search.getTerm().sendKeys("test");
	search.getTerm().sendKeys("\n");
	// final RenderedWebElement resultName =
	// search.getResultName(SeleniumConstants.USERJID);
	// final RenderedWebElement resultMenu =
	// search.getResultMenu(SeleniumConstants.USERJID);
    }

}
