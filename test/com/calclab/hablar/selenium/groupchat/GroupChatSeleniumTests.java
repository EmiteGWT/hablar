package com.calclab.hablar.selenium.groupchat;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.calclab.hablar.rooms.client.RoomsMessages;
import com.calclab.hablar.selenium.HablarSeleniumTest;
import com.calclab.hablar.selenium.tools.I18nHelper;

public class GroupChatSeleniumTests extends HablarSeleniumTest {
    private I18nHelper i18n;

    @BeforeClass
    public void beforeClass() {
	i18n = new I18nHelper(RoomsMessages.class);
    }

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
	final String newtestgroup = "testgroup" + getTempString();
	addToGroup(newtestgroup);
	roster.getGroupMenu(newtestgroup).click();
	roster.getOpenGroupChat().click();
	groupChat.getOpenGroupChatAccept().click();
	groupChat.getRoomHeader("1").click();
	groupChat.waitForTextInRoom("1", "This group chat is now unlocked");
	groupChat.getRoomTextBox("1").sendKeys("hi!\n");
	groupChat.waitForTextInRoom("1", "hi!");
	groupChat.waitForStatus("1", i18n.get("occupantHasJoined", "selenium"));
	groupChat.waitForStatus("1", i18n.get("occupants", Integer.valueOf(1)));
	removeFromGroup(newtestgroup);
	removeSeleniumBuddy();
	logout();
    }
}
