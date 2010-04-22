package com.calclab.hablar.selenium.clipboard;

import org.testng.annotations.Test;

import com.calclab.hablar.selenium.HablarSeleniumTest;

public class CopyToClipboardTests extends HablarSeleniumTest {

    @Test
    public void shouldShowCopyToClipboardPage() {
	login();
	addSeleniumBuddy();
	openChat.getAction().click();
	final String userJid = "selenium@localhost";
	openChat.getJabberId().sendKeys(userJid);
	openChat.getOpen().click();
	final String message = "test message " + System.currentTimeMillis();
	chat.getTalkBox(userJid).sendKeys(message);
	chat.getSend(userJid).click();
	copyToClipboard.getAction().click();
	copyToClipboard.getClose().click();
	logout();
    }

    @Test
    public void testCopyToClipboardPageForGroupChat() {
	login();

	String[] jids = {};
	openGroupChat(jids);

	groupChat.getRoomHeader("1").click();
	groupChat.waitForTextInRoom("1", "This group chat is now unlocked");
	final String message = "test message " + System.currentTimeMillis();
	groupChat.getRoomTextBox("1").sendKeys(message + "\n");
	groupChat.waitForTextInRoom("1", message);

	// This is weird. It seems to only work if I call click loads of times!
	copyToClipboard.getAction().click();
	copyToClipboard.getAction().click();
	copyToClipboard.getAction().click();
	copyToClipboard.getAction().click();
	copyToClipboard.getAction().click();
	copyToClipboard.getAction().click();

	copyToClipboard.waitForMessage(message);
	copyToClipboard.getClose().click();
	logout();
    }
}
