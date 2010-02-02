package com.calclab.hablar.selenium.chat;

import org.testng.annotations.Test;

import com.calclab.hablar.selenium.HablarSeleniumTest;
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
	chat.getTalkBox().sendKeys(Lorem.latin);
	chat.getTalkBox().sendKeys(Lorem.chinese);
	chat.getTalkBox().sendKeys(":P\n");
	chat.waitFor(":P\n");
    }

}
