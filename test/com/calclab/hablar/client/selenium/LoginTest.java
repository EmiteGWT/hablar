package com.calclab.hablar.client.selenium;

import junit.framework.Assert;

import com.calclab.hablar.client.i18n.Msg;

public class LoginTest extends SeleniumTestSuite {
    private final WebTest basicLogin;

    public LoginTest() {
	basicLogin = new WebTest() {
	    @Override
	    public void run(final WebContext ctx) {
		final AbstractWebTester webHelper = ctx.getWebHelper();
		webHelper.home();
		final LoginPageTest login = new LoginPageTest(webHelper);
		login.click();
		Assert.assertTrue(login.getHeader().getText().contains(Msg.DISCONNECTED));
		login.as("admin@localhost", "easyeasy");
		Assert.assertTrue(login.getHeader().getText().contains(Msg.CONNECTED_AS));
		login.click();
	    }
	};
    }

    @Override
    public void registerTests() {
	add("Basic login", basicLogin);
    }
}
