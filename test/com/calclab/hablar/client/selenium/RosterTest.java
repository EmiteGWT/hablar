package com.calclab.hablar.client.selenium;

import org.junit.Assert;

import com.calclab.hablar.client.i18n.Msg;

public class RosterTest extends SeleniumTestSuite {

    private final WebTest rosterPanelSelectionNotLogged;

    public RosterTest() {
	rosterPanelSelectionNotLogged = new WebTest() {
	    @Override
	    public void run(final WebContext ctx) {
		final AbstractWebTester webHelper = ctx.getWebHelper();
		webHelper.home();
		final RosterPageTest roster = new RosterPageTest(webHelper);
		roster.click();
		Assert.assertTrue("Roster disabled label not visible", roster.getDisableLabel().getText().contains(
			Msg.ROSTER_DISABLED));
		webHelper.wait(1000);
	    }
	};
    }

    @Override
    public void registerTests() {
	add("Roster panel selection when not logged", rosterPanelSelectionNotLogged);
    }
}
