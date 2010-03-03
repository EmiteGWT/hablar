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

    @FindBy(id = "gwt-debug-HablarRoster-removeFromRosterAction")
    private RenderedWebElement removeBuddyAction;

    @FindBy(id = "gwt-debug-HablarRoster-addToGroupAction")
    private RenderedWebElement addBuddyToGroupAction;

    @FindBy(id = "gwt-debug-EditBuddy-editAction")
    private RenderedWebElement editBuddyAction;

    @FindBy(id = "gwt-debug-HablarVCard-seeVCardAction")
    private RenderedWebElement seeBuddyVCardAction;

    public RosterPageObject() {
    }

    public RenderedWebElement getAddToGroupAction() {
	return addBuddyToGroupAction;
    }

    public RenderedWebElement getDisableLabel() {
	return disabledLabel;
    }

    public RenderedWebElement getEditBuddyAction() {
	return editBuddyAction;
    }

    public RenderedWebElement getHeader() {
	return header;
    }

    public RenderedWebElement getItemMenu(final String groupId, final String jid) {
	final String id = Idify.id("RosterItemWidget", groupId, Idify.uriId(jid), "roster-menu");
	return findElement(new ByIdOrName("gwt-debug-" + id));
    }

    public RenderedWebElement getRemoveBuddyAction() {
	return removeBuddyAction;
    }

    public RenderedWebElement getRosterItem(final String groupId, final String jid) {
	final String id = Idify.id("RosterItemWidget", groupId, Idify.uriId(jid));
	return findElement(new ByIdOrName("gwt-debug-" + id));
    }

    public RenderedWebElement getSeeBuddyVCardAction() {
	return seeBuddyVCardAction;
    }
}
