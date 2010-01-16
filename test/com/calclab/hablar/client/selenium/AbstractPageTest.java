package com.calclab.hablar.client.selenium;

import org.openqa.selenium.WebElement;

public class AbstractPageTest extends AbstractWebElement {

    private final String pageId;

    public AbstractPageTest(final WebTester tester, final String pageId) {
	super(tester);
	this.pageId = pageId;
    }

    public void click() {
	final WebElement panel = getHeader();
	panel.click();
    }

    public WebElement getHeader() {
	return getById(pageId);
    }

    public WebElement Header() {
	return getHeader();
    }

}
