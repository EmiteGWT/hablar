package com.calclab.hablar.selenium.groupchat;

import org.testng.annotations.Test;

import com.calclab.hablar.selenium.roster.AbstractRosterTests;

public class GroupChatSeleniumTests extends AbstractRosterTests {

    @Test
    public void shouldInstallActionOnChats() {
	login();
	openChat.getAction().click();
	openChat.getJabberId().sendKeys("selenium@localhost");
	openChat.getOpen().click();
	logout();
    }

    @Test
    public void shouldSendMessagesToGroups() {
	login();
	addSeleniumBuddy();
	addToGroup("testgroup");
	roster.getGroupMenu("testgroup").click();
	roster.getOpenGroupChat().click();
	groupChat.getOpenGroupChatAccept().click();
	groupChat.getRoomHeader("1").click();
	groupChat.waitForTextInRoom("1", "This room is now unlocked");
	groupChat.getRoomTextBox("1").sendKeys("hi!\n");
	groupChat.waitForTextInRoom("1", "hi!");
	removeFromGroup("testgroup");
	removeSeleniumBuddy();
	logout();
    }
}
