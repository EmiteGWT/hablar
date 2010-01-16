package com.calclab.hablar.client.selenium;

import java.util.HashMap;
import java.util.Map;

public abstract class SeleniumTestSuite {

    private Map<String, WebTest> tests;

    public SeleniumTestSuite() {
    }

    public void add(final String name, final WebTest test) {
	tests.put(name, test);
    }

    public Map<String, WebTest> getTests() {
	tests = new HashMap<String, WebTest>();
	registerTests();
	return tests;
    }

    public abstract void registerTests();
}
