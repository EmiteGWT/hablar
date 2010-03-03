package com.calclab.hablar.selenium.groupchat;

import org.testng.annotations.Test;

import com.calclab.hablar.selenium.HablarSeleniumTest;

public class GroupChatSeleniumTests extends HablarSeleniumTest {

    @Test
    public void shouldInstallActionOnChats() {
	login();
	openChat.getAction().click();
	openChat.getJabberId().sendKeys("selenium@localhost");
	openChat.getOpen().click();

	logout();
    }
}
