package com.calclab.hablar.client.selenium;

import org.openqa.selenium.WebElement;

public abstract class AbstractWebElement {

    private final WebTester tester;

    public AbstractWebElement(final WebTester tester) {
	this.tester = tester;
    }

    public WebElement getById(final String id) {
	return tester.getById(id);
    }

    protected void waitForTextInside(final WebElement element, final String text) throws Exception {
	for (int second = 0;; second++) {
	    if (second >= 60) {
		// FIXME fail("timeout");
	    }
	    try {
		final String selText = element.getText();
		if (selText.indexOf(text) >= 0) {
		    break;
		}
	    } catch (final Exception e) {
	    }
	    Thread.sleep(1000);
	}
    }

    protected void waitForTextRegExp(final WebElement element, final String text) throws Exception {
	for (int second = 0;; second++) {
	    if (second >= 60) {
		// FIXME fail("timeout");
	    }
	    try {
		if (element.getText().matches(text)) {
		    break;
		}
	    } catch (final Exception e) {
	    }
	    Thread.sleep(1000);
	}
    }
}
