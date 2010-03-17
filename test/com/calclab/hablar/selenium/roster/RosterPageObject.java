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

    @FindBy(id = "gwt-debug-HablarGroupChat-openGroupChatAction")
    private RenderedWebElement openGroupChat;

    @FindBy(id = "gwt-debug-HablarRoster-removeFromGroupAction")
    private RenderedWebElement removeFromGroupAction;

    @FindBy(id = "gwt-debug-HablarRoster-removeFromRosterAction")
    private RenderedWebElement removeBuddyAction;

    @FindBy(id = "gwt-debug-HablarRoster-manageGroupsAction")
    private RenderedWebElement manageGroupsAction;

    @FindBy(id = "gwt-debug-EditBuddy-editAction")
    private RenderedWebElement editBuddyAction;

    @FindBy(id = "gwt-debug-HablarVCard-seeVCardAction")
    private RenderedWebElement seeBuddyVCardAction;

    @FindBy(id = "gwt-debug-ManageGroupsWidget-accept")
    private RenderedWebElement manageGroupsAccept;

    @FindBy(id = "gwt-debug-HablarRoster-deleteGroupAction")
    private RenderedWebElement deleteGroupAction;

    @FindBy(id = "gwt-debug-ManageGroupsWidget-newGroup")
    private RenderedWebElement createNewGroupButton;

    @FindBy(id = "gwt-debug-GroupSelectorWidget-editableName")
    private RenderedWebElement newGroupField;

    public RosterPageObject() {
    }

    public RenderedWebElement getCreateNewGroupButton() {
	return createNewGroupButton;
    }

    public RenderedWebElement getDeleteGroupAction() {
	return deleteGroupAction;
    }

    public RenderedWebElement getDisableLabel() {
	return disabledLabel;
    }

    public RenderedWebElement getEditBuddyAction() {
	return editBuddyAction;
    }

    public RenderedWebElement getGroup(final String groupId) {
	final String id = Idify.id("GroupHeaderWidget-name", groupId);
	return findElement(new ByIdOrName("gwt-debug-" + id));
    }

    public RenderedWebElement getGroupMenu(final String groupId) {
	final String id = Idify.id("GroupHeaderWidget-menu", groupId);
	return findElement(new ByIdOrName("gwt-debug-" + id));
    }

    public RenderedWebElement getHeader() {
	return header;
    }

    public RenderedWebElement getItemMenu(final String groupId, final String jid) {
	final String id = Idify.id("RosterItemWidget", groupId, Idify.uriId(jid), "roster-menu");
	return findElement(new ByIdOrName("gwt-debug-" + id));
    }

    public RenderedWebElement getManageGroupsAccept() {
	return manageGroupsAccept;
    }

    public RenderedWebElement getManageGroupsAction() {
	return manageGroupsAction;
    }

    public RenderedWebElement getNewGroupField() {
	return newGroupField;
    }

    public RenderedWebElement getOpenGroupChat() {
	return openGroupChat;
    }

    public RenderedWebElement getRemoveBuddyAction() {
	return removeBuddyAction;
    }

    public RenderedWebElement getRemoveFromGroupAction() {
	return removeFromGroupAction;
    }

    public RenderedWebElement getRosterItem(final String groupId, final String jid) {
	final String id = Idify.id("RosterItemWidget", groupId, Idify.uriId(jid));
	return findElement(new ByIdOrName("gwt-debug-" + id));
    }

    public RenderedWebElement getSeeBuddyVCardAction() {
	return seeBuddyVCardAction;
    }

    public void waitForItemMenu(final String groupId, final String jid) {
	waitForId("gwt-debug-" + Idify.id("RosterItemWidget", groupId, Idify.uriId(jid), "roster-menu"));
    }
}
