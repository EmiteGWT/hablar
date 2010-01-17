package com.calclab.hablar.client.selenium;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.client.HablarLogic;
import com.calclab.hablar.client.roster.RosterPageWidget;

public class RosterPageObject extends AbstractPageObject {

    @FindBy(id = Debug.PRE + RosterPageWidget.DISABLED_LABEL)
    private RenderedWebElement disabledLabel;

    @FindBy(id = Debug.PRE + RosterPageWidget.ID)
    private RenderedWebElement header;

    @FindBy(id = Debug.PRE + HablarLogic.SEARCH_ICON)
    private RenderedWebElement searchIcon;

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

    public WebElement SearchIcon() {
	return searchIcon;
    }

}
