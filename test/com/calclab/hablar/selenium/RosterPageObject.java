package com.calclab.hablar.selenium;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.basic.client.ui.HablarLogic;
import com.calclab.hablar.basic.client.ui.utils.DebugId;
import com.calclab.hablar.roster.client.RosterItemLogic;
import com.calclab.hablar.roster.client.RosterPageWidget;

public class RosterPageObject extends AbstractPageObject {

    @FindBy(id = DebugId.PRE + RosterPageWidget.DISABLED_LABEL)
    private RenderedWebElement disabledLabel;

    @FindBy(id = DebugId.PRE + RosterPageWidget.ID)
    private RenderedWebElement header;

    @FindBy(id = DebugId.PRE + HablarLogic.SEARCH_ICON)
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
	return findElement(new ByIdOrName(DebugId.getFromJid(RosterItemLogic.ROSTERITEM_MENU_DEB_ID, jid)));
    }

    public RenderedWebElement Header() {
	return getHeader();
    }

    public WebElement SearchIcon() {
	return searchIcon;
    }

}
