package com.calclab.hablar.selenium;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;

/**
 * Shared behaviour in selenium tests
 */
public abstract class HablarSeleniumTest extends HablarSeleniumDefaults {
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
}
