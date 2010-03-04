package com.calclab.hablar.selenium.chat;

import org.testng.annotations.Test;

import com.calclab.hablar.selenium.HablarSeleniumTest;
import com.calclab.hablar.selenium.tools.Lorem;

public class ChatSeleniumTests extends HablarSeleniumTest {

    @Test
    public void shouldHaveSendButton() {
	final String uri = "test1@localhost";
	login(uri, "test1");
	openChat(uri);
	chat.getSend(uri);
    }

    @Test
    public void shouldSendAndReceiveText() {
	final String user = "selenium@localhost";
	login(user, "selenium");
	openChat(user);
	chat.getHeader(user);
	chat.getTalkBox(user).sendKeys("hi!\n");
	chat.waitFor(user, "hi!");
	chat.waitFor(user, "selenium");
    }

    @Test
    public void shouldSendText() {
	final String user = "test1@localhost";
	login(user, "test1");
	openChat(user);
	chat.getHeader(user);
	chat.getTalkBox(user).sendKeys(Lorem.latin);
	chat.getTalkBox(user).sendKeys(Lorem.chinese);
	chat.getTalkBox(user).sendKeys("what?\n");
	chat.waitFor(user, "what?\n");
    }

}
