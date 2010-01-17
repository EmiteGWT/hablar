package com.calclab.hablar.client.selenium;

import org.junit.Assert;

public class RosterTest extends SeleniumTestSuite {

    private final WebTest rosterPanelSelectionNotLogged;
    private final WebTest rosterPanelSelectionNotLoggedNotVisible;

    public RosterTest(final RosterPageObject roster) {
	rosterPanelSelectionNotLoggedNotVisible = new WebTest() {
	    @Override
	    public void run(final WebContext ctx) {
		final AbstractWebTester webHelper = ctx.getWebHelper();
		webHelper.home();
		// FIXME: this must fail...
		Assert.assertTrue("Roster disabled label visible", roster.getDisableLabel().isDisplayed());
	    }
	};
	rosterPanelSelectionNotLogged = new WebTest() {
	    @Override
	    public void run(final WebContext ctx) {
		final AbstractWebTester webHelper = ctx.getWebHelper();
		webHelper.home();
		roster.Header().click();
		Assert.assertTrue("Roster disabled label not visible", roster.getDisableLabel().isDisplayed());
	    }
	};
    }

    @Override
    public void registerTests() {
	add("Roster panel selection when not logged", rosterPanelSelectionNotLogged);
	add("Roster panel selection when not logged not visible", rosterPanelSelectionNotLoggedNotVisible);
    }
}
