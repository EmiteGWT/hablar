package com.calclab.hablar.client.selenium;

public class SeleniumTestApp {
    public static void main(final String[] args) throws Exception {

	// add tests here

	// FIXME: While sharing classes with Functional tests
	new LoginTest().run(new WebContext(new HostedWebTester()));
    }
}
