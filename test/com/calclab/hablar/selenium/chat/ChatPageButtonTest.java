package com.calclab.hablar.selenium.chat;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.calclab.hablar.selenium.HablarSeleniumTest;
import com.calclab.hablar.selenium.SeleniumConstants;
import com.calclab.hablar.selenium.login.LoginPageObject;
import com.calclab.hablar.selenium.openchat.OpenChatPageObject;
import com.calclab.hablar.selenium.roster.RosterPageObject;
import com.calclab.suco.client.Suco;

public class ChatPageButtonTest extends HablarSeleniumTest {

    private LoginPageObject login;
    private RosterPageObject roster;
    private OpenChatPageObject openChat;
    private ChatPageObject chat;

    @BeforeClass
    public void beforeClass() {
	login = Suco.get(LoginPageObject.class);
	roster = Suco.get(RosterPageObject.class);
	openChat = Suco.get(OpenChatPageObject.class);
	chat = Suco.get(ChatPageObject.class);
    }

    @Test
    public void shouldHaveSendButton() {
	login.signInDefUser();
	login.assertIsConnectedAs(SeleniumConstants.USERNODE);
	roster.Header().click();
	roster.OpenChatIcon().click();
	openChat.getJabberId().sendKeys("test1@localhost");
	openChat.getOpen().click();
	chat.getSendButton();
    }

}
