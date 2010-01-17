package com.calclab.hablar.client.selenium;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.client.roster.RosterPageWidget;

public class RosterPageObject {

    @FindBy(id = Debug.PRE + RosterPageWidget.DISABLED_LABEL)
    private RenderedWebElement disabledLabel;

    @FindBy(id = Debug.PRE + RosterPageWidget.ID)
    private RenderedWebElement header;

    public RosterPageObject() {
    }

    public RenderedWebElement getDisableLabel() {
	return disabledLabel;
    }

    public RenderedWebElement getHeader() {
	return header;
    }

    public RenderedWebElement Header() {
	return getHeader();
    }

}
