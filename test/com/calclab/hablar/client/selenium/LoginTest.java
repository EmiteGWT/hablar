package com.calclab.hablar.client.selenium;

public class LoginTest implements WebTest {

    @Override
    public void run(final WebContext ctx) {
	final AbstractWebTester webHelper = ctx.getWebHelper();
	webHelper.home();
	final LoginPageTest login = new LoginPageTest(webHelper);
	final RosterPageTest roster = new RosterPageTest(webHelper);
	login.focus();
	login.as("admin@localhost", "easyeasy");
	// FIXME use constants/resources here:
	webHelper.wait(2000);
	assertTrue(login.hasHeader(".*Connected.*"));
	// only for test
	login.focus();
	webHelper.wait(1000);
	roster.focus();
	webHelper.close();
    }

    private void assertTrue(final boolean object) {
	if (object == false) {
	    // do something
	}
    }
}
