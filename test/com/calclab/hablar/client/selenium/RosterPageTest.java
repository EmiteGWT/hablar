package com.calclab.hablar.client.selenium;

import org.openqa.selenium.WebElement;

import com.calclab.hablar.client.roster.RosterPageWidget;

public class RosterPageTest extends AbstractPageTest {

    public RosterPageTest(final WebTester tester) {
	super(tester, RosterPageWidget.ID);
    }

    public WebElement getDisableLabel() {
	return getById(RosterPageWidget.DISABLED_LABEL);
    }

}
