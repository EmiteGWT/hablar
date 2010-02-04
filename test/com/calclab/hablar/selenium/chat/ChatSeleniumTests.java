package com.calclab.hablar.selenium.chat;

import org.testng.annotations.Test;

import com.calclab.hablar.selenium.HablarSeleniumTest;
import com.calclab.hablar.selenium.tools.Lorem;

public class ChatSeleniumTests extends HablarSeleniumTest {

    @Test
    public void shouldHaveSendButton() {
	String uri = "test1@localhost";
	login(uri, "test1");
	openChat(uri);
	chat.getSend(uri);
    }

    @Test
    public void shouldSendText() {
	String user = "test1@localhost";
	login(user, "test1");
	openChat(user);
	chat.getHeader(user);
	chat.getTalkBox(user).sendKeys(Lorem.latin);
	chat.getTalkBox(user).sendKeys(Lorem.chinese);
	chat.getTalkBox(user).sendKeys(":P\n");
	chat.waitFor(user, ":P\n");
    }

}
