package com.calclab.hablar.selenium.roster;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.support.ByIdOrName;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.core.client.Idify;
import com.calclab.hablar.selenium.PageObject;

public class RosterPageObject extends PageObject {

    @FindBy(id = "gwt-debug-RosterWidget-disabledPanel")
    private RenderedWebElement disabledLabel;

    @FindBy(id = "gwt-debug-HeaderWidget-Roster-1")
    private RenderedWebElement header;

    public RosterPageObject() {
    }

    public RenderedWebElement getDisableLabel() {
	return disabledLabel;
    }

    public RenderedWebElement getHeader() {
	return header;
    }

    public RenderedWebElement getItemMenu(final String groupId, final String jid) {
	final String id = Idify.id("RosterItemWidget", groupId, Idify.uriId(jid), "roster-menu");
	return findElement(new ByIdOrName("gwt-debug-" + id));
    }

    public RenderedWebElement getRosterItem(final String groupId, final String jid) {
	final String id = Idify.id("RosterItemWidget", groupId, Idify.uriId(jid));
	return findElement(new ByIdOrName("gwt-debug-" + id));
    }
}
