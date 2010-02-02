package com.calclab.hablar.selenium;

/**
 * Shared behaviour in selenium tests
 * 
 */
public abstract class HablarSeleniumTest extends HablarSeleniumDefaults {
    protected void login(String user) {
	login.signIn(user + "@localhost", user);
	login.assertIsConnectedAs(user);
    }

    protected void openChat(String user) {
	roster.getHeader().click();
	roster.OpenChatIcon().click();
	openChat.getJabberId().sendKeys(user + "@localhost");
	openChat.getOpen().click();
    }
}
