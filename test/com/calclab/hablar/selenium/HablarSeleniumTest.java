package com.calclab.hablar.selenium;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;

/**
 * Shared behaviour in selenium tests
 */
public abstract class HablarSeleniumTest extends HablarSeleniumDefaults {
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
	roster.getManageGroupsAction().click();
	roster.getCreateNewGroupButton().click();
	roster.getNewGroupField().sendKeys(group);
	roster.getManageGroupsAccept().click();
    }

    protected String getTempString() {
	final DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	final String value = dateFormat.format(new Date());
	return value;
    }

    protected void login() {
	login("selenium@localhost", "selenium");
    }

    protected void login(final String jid, final String password) {
	final XmppURI uri = XmppURI.uri(jid);
	login.signIn(jid, password);
	login.assertIsConnectedAs(uri.getShortName());
    }

    protected void logout() {
	login.logout();
    }

    protected void openChat(final String jid) {
	roster.getHeader().click();
	openChat.getAction().click();
	openChat.getJabberId().sendKeys(jid);
	openChat.getOpen().click();
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
