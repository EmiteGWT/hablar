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
}
