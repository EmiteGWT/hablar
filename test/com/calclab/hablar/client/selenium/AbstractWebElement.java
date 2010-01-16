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

}
