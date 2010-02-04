package com.calclab.hablar.selenium.openchat;

import org.testng.annotations.Test;

import com.calclab.hablar.selenium.HablarSeleniumTest;

public class OpenChatSeleniumTests extends HablarSeleniumTest {

    @Test
    public void shouldDisplayOpenChatWidget() {
	login("test1@localhost", "test1");
	openChat.getAction().click();
	openChat.getWidget();
	openChat.getOpen();
	openChat.getCancel();
    }

    @Test
    public void shouldInstallOpenChatIconOnRoster() {
	roster.getHeader().click();
	openChat.getAction();
    }

    @Test
    public void shouldOpenChat() {
	login("test1@localhost", "test1");
	openChat.getAction().click();
	openChat.getJabberId().sendKeys("test1@localhost");
	openChat.getOpen().click();
	chat.getHeader("test1@localhost");
    }
}
