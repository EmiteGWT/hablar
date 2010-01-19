package com.calclab.hablar.basic.client.selenium;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.basic.client.HablarLogic;
import com.calclab.hablar.basic.client.roster.RosterItemLogic;
import com.calclab.hablar.basic.client.roster.RosterPageWidget;
import com.calclab.hablar.basic.client.ui.debug.Debug;

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

    public RenderedWebElement getItemMenu(final String jid) {
	return findElement(new ByIdOrName(Debug.getIdFromJid(RosterItemLogic.ROSTERITEM_MENU_DEB_ID, jid)));
    }

    public RenderedWebElement Header() {
	return getHeader();
    }

    public WebElement SearchIcon() {
	return searchIcon;
    }

}
