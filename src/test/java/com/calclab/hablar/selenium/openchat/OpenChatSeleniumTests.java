package com.calclab.hablar.selenium.openchat;

import org.testng.annotations.Test;

import com.calclab.hablar.selenium.HablarSeleniumTest;

public class OpenChatSeleniumTests extends HablarSeleniumTest {

    @Test
    public void shouldAddToRoster() {
	login();
	openChat.getAction().click();
	openChat.getJabberId().sendKeys("test1@localhost");
	openChat.getAddToRoster().click();
	openChat.getOpen().click();
	sleep(1000);
	roster.getRosterItem(null, "test1@localhost");
	login.getHeader().click();
	logout();
    }

    @Test
    public void shouldDisplayOpenChatWidget() {
	login("selenium@selenium", "selenium");
	openChat.getAction().click();
	openChat.getWidget();
	openChat.getOpen();
	openChat.getCancel().click();
	logout();
    }

    @Test
    public void shouldInstallOpenChatIconOnRoster() {
	login("selenium@selenium", "selenium");
	roster.getHeader().click();
	openChat.getAction();
	logout();
    }

    @Test
    public void shouldOpenChat() {
	login("selenium@selenium", "selenium");
	openChat.getAction().click();
	openChat.getJabberId().sendKeys("test1@localhost");
	openChat.getOpen().click();
	chat.getHeader("test1@localhost");
    }

}
