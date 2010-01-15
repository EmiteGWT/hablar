package com.calclab.hablar.client.selenium;

import org.openqa.selenium.WebElement;

import com.calclab.hablar.client.roster.RosterPage;

public class RosterPageTest extends AbstractWebElement {

    public RosterPageTest(final WebTester tester) {
	super(tester);
    }

    public void focus() {
	final WebElement panel = mainPanel();
	panel.click();
    }

    private WebElement mainPanel() {
	return getById(RosterPage.ID);
    }

}
