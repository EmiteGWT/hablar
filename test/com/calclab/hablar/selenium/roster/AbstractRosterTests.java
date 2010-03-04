package com.calclab.hablar.selenium.roster;

import com.calclab.hablar.selenium.HablarSeleniumTest;

public class AbstractRosterTests extends HablarSeleniumTest {

    protected void addBuddy(final String jid) {
	openChat.getAction().click();
	openChat.getJabberId().sendKeys(jid);
	openChat.getAddToRoster().click();
	openChat.getOpen().click();
	roster.getHeader().click();
	roster.waitForItemMenu("", jid);
    }

    protected void addSeleniumBuddy() {
	addBuddy("selenium@localhost");
    }

    protected void addToGroup(final String group) {
	roster.getHeader().click();
	roster.getItemMenu("", "selenium@localhost").click();
	roster.getAddToGroupAction().click();
	roster.getAddToGroupNewGroupName().sendKeys(group);
	roster.getAddToGroupAccept().click();
    }

    protected void removeBuddy(final String groupId, final String jid) {
	roster.getHeader().click();
	roster.getItemMenu(groupId, jid).click();
	roster.getRemoveBuddyAction().click();
    }

    protected void removeFromGroup(final String group) {
	roster.getHeader().click();
	roster.getGroup(group).click();
	roster.getItemMenu(group, "selenium@localhost").click();
	roster.getRemoveFromGroupAction().click();
    }

    protected void removeSeleniumBuddy() {
	removeBuddy("", "selenium@localhost");
    }

}
