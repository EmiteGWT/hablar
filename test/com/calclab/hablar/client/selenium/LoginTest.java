package com.calclab.hablar.client.selenium;

import junit.framework.Assert;

import com.calclab.hablar.client.i18n.Msg;

public class LoginTest extends SeleniumTestSuite {
    private final WebTest basicLogin;

    public LoginTest(final LoginPageObject login) {
	basicLogin = new WebTest() {
	    @Override
	    public void run(final WebContext ctx) {
		final AbstractWebTester webHelper = ctx.getWebHelper();
		webHelper.home();
		login.getHeader().click();
		Assert.assertTrue(login.Header().getText().contains(Msg.DISCONNECTED));
		login.as("test1@localhost", "test1");
		Assert.assertTrue(login.Header().getText().contains(Msg.CONNECTED_AS));
		login.getHeader().click();
	    }
	};
    }

    @Override
    public void registerTests() {
	add("Basic login", basicLogin);
    }
}
