package com.calclab.hablar.client.selenium;

import java.util.ArrayList;
import java.util.Map;

import org.mortbay.log.Log;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;

public class SeleniumTestApp {

    private static ArrayList<SeleniumTestSuite> suites;

    public static void main(final String[] args) throws Exception {
	suites = new ArrayList<SeleniumTestSuite>();

	final FirefoxDriver firefoxDriver = createDriver();
	// final WebDriverWait wait = new WebDriverWait(firefoxDriver, 120);

	final WebContext ctx = new WebContext(new GenericWebTester(firefoxDriver,
		"http://localhost:8888/Hablar.html?gwt.codesvr=127.0.1.1:9997"));

	// Maybe use here Suco?
	final RosterPageObject roster = PageFactory.initElements(firefoxDriver, RosterPageObject.class);
	final LoginPageObject login = PageFactory.initElements(firefoxDriver, LoginPageObject.class);

	// add tests here
	register(new RosterTest(roster));
	register(new LoginTest(login));
	ctx.getWebHelper().wait(5000);

	run(ctx);
    }

    private static FirefoxDriver createDriver() {
	// http://code.google.com/p/selenium/wiki/FirefoxDriver
	// System.getProperties().put("webdriver.firefox.useExisting", "true");
	// System.setProperty("webdriver.firefox.useExisting", "true");

	// This is the profile of your browser (in you use OOPHM you must
	// install the GWT plugin in the "selenium" profile
	// See:
	// http://code.google.com/intl/es-ES/webtoolkit/doc/latest/DevGuideTestingRemoteTesting.html#Firefox_Profile
	final FirefoxDriver firefoxDriver = new FirefoxDriver("selenium");
	return firefoxDriver;
    }

    private static void register(final SeleniumTestSuite suite) {
	suites.add(suite);
    }

    private static void run(final WebContext ctx) {
	for (final SeleniumTestSuite suite : suites) {
	    final Map<String, WebTest> tests = suite.getTests();
	    for (final String name : tests.keySet()) {
		Log.info("Running test: " + name);
		final WebTest test = tests.get(name);
		test.run(ctx);
	    }
	}
	ctx.getWebHelper().close();
    }
}
