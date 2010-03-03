package com.calclab.hablar.selenium;

import java.awt.Point;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;

import com.calclab.hablar.selenium.chat.ChatPageObject;
import com.calclab.hablar.selenium.login.LoginPageObject;
import com.calclab.hablar.selenium.openchat.OpenChatPageObject;
import com.calclab.hablar.selenium.roster.RosterPageObject;
import com.calclab.hablar.selenium.search.SearchPageObject;
import com.calclab.hablar.selenium.tools.GenericWebTester;
import com.calclab.hablar.selenium.tools.SeleniumConstants;
import com.calclab.hablar.selenium.tools.SeleniumModule;
import com.calclab.suco.client.Suco;

public class HablarSeleniumDefaults {
    public static boolean mustCloseFinally = true;
    protected LoginPageObject login;
    protected RosterPageObject roster;
    protected OpenChatPageObject openChat;
    protected ChatPageObject chat;
    protected SearchPageObject search;
    protected GenericWebTester webtester;

    @AfterSuite
    public void closeBrowser() {
	// We try to only open one window for all our selenium tests
	if (mustCloseFinally) {
	    webtester.close();
	}
    }

    @DataProvider(name = "correctlogin")
    public Object[][] createCorrectLogin() {
	// The default correct user/password used in tests
	return new Object[][] { { SeleniumConstants.USERJID, SeleniumConstants.PASSWD, SeleniumConstants.USERNODE }, };
    }

    @DataProvider(name = "incorrectlogin")
    public Object[][] createIncorrectLogin() {
	// Some pairs of user/passwd that must fail when try to login
	return new Object[][] { { "test1@localhost", "test1blabla" }, { "test1", "test1" },
		{ "test1@localhost", "test" }, { "", "" } };
    }

    @BeforeMethod
    public void goHome() {
	webtester.home();
    }

    public void moveMouseAt(final Point point) {
	webtester.moveMouseAt(point);
    }

    @BeforeTest
    public void setupSeleniumModule(final ITestContext context) {
	if (!Suco.getComponents().hasProvider(WebDriver.class)) {
	    Suco.install(new SeleniumModule());
	}
	webtester = Suco.get(GenericWebTester.class);
	login = Suco.get(LoginPageObject.class);
	roster = Suco.get(RosterPageObject.class);
	openChat = Suco.get(OpenChatPageObject.class);
	search = Suco.get(SearchPageObject.class);
	chat = Suco.get(ChatPageObject.class);
    }

    public void sleep(final int milliseconds) {
	try {
	    Thread.sleep(milliseconds);
	} catch (final InterruptedException e) {
	    Assert.fail("Exception in sleep method", e);
	}
    }
}
