package com.calclab.hablar.client.selenium;

import java.util.ArrayList;
import java.util.Map;

import org.mortbay.log.Log;

public class SeleniumTestApp {

    private static ArrayList<SeleniumTestSuite> suites;

    public static void main(final String[] args) throws Exception {
	suites = new ArrayList<SeleniumTestSuite>();

	final WebContext ctx = new WebContext(new FirefoxWebTester(
		"http://localhost:8888/Hablar.html?gwt.codesvr=127.0.1.1:9997"));

	// add tests here
	register(new RosterTest());
	register(new LoginTest());

	run(ctx);
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
