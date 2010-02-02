package com.calclab.hablar.selenium.chat;

import org.testng.annotations.Test;

import com.calclab.hablar.selenium.tools.HablarSeleniumTest;
import com.calclab.hablar.selenium.tools.Lorem;

public class ChatPageSeleniumTests extends HablarSeleniumTest {

    @Test
    public void shouldBeAbleToSendText() {

    }

    @Test
    public void shouldHaveSendButton() {
	login("test1");
	openChat("test1");
	chat.getSendButton();
    }

    @Test
    public void shouldSendText() {
	login("test1");
	openChat("test1");
	chat.TalkBox().sendKeys(Lorem.latin);
	chat.TalkBox().sendKeys(Lorem.chinese);
	chat.TalkBox().sendKeys(":P\n");
	chat.waitFor(":P\n");
    }

    private void login(String user) {
	login.signIn(user + "@localhost", user);
	login.assertIsConnectedAs(user);
    }

    private void openChat(String user) {
	roster.getHeader().click();
	roster.OpenChatIcon().click();
	openChat.getJabberId().sendKeys(user + "@localhost");
	openChat.getOpen().click();
    }
}
