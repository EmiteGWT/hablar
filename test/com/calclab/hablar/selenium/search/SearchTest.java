package com.calclab.hablar.selenium.search;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.calclab.hablar.selenium.HablarSeleniumTest;
import com.calclab.hablar.selenium.I18nHelper;
import com.calclab.hablar.selenium.Lorem;
import com.calclab.hablar.selenium.SeleniumConstants;
import com.calclab.hablar.selenium.chat.ChatPageObject;
import com.calclab.hablar.selenium.login.LoginPageObject;
import com.calclab.hablar.selenium.roster.RosterPageObject;
import com.calclab.suco.client.Suco;

public class SearchTest extends HablarSeleniumTest {
    private LoginPageObject login;
    private RosterPageObject roster;
    private SearchPageObject search;
    private I18nHelper i18n;
    private ChatPageObject chat;

    @BeforeClass
    public void beforeClass() {
	login = Suco.get(LoginPageObject.class);
	roster = Suco.get(RosterPageObject.class);
	search = Suco.get(SearchPageObject.class);
	i18n = Suco.get(I18nHelper.class);
	chat = Suco.get(ChatPageObject.class);
    }

    @Test()
    public void testBasicSearch() {
	loginAndSearchClick();
	search.term("test1");
	sleep(5000);
	search.waitForResult(i18n.get("searchResultsFor", "test1", 1));
    }

    @Test()
    public void testBasicSearchAndChat() {
	loginSearchTestUserAndChat();
	chat.TalkBox().sendKeys("Hi ;)\n");
	chat.TalkBox().sendKeys("some echo tests\n");
	// Uncomment to don't close the window
	// mustCloseFinally = false;
    }

    @Test()
    public void testBasicSearchNoResult() {
	loginAndSearchClick();
	search.term("Something difficult to search");
	sleep(5000);
	// zero it's plural in English
	search.waitForResult(i18n.get("searchResultsFor", "Something difficult to search", 0));
    }

    @Test()
    public void testChatLoremPaste() {
	loginSearchTestUserAndChat();
	chat.TalkBox().sendKeys(Lorem.latin);
	chat.TalkBox().sendKeys(Lorem.chinese);
	chat.TalkBox().sendKeys(":P\n");
	chat.waitFor(":P\n");
    }

    private void loginAndSearchClick() {
	login.signInDefUser();
	login.assertIsConnectedAs(SeleniumConstants.USERNODE);
	roster.Header().click();
	roster.SearchIcon().click();
	// Only to test accordion
	roster.Header().click();
	search.Header().click();
    }

    private void loginSearchTestUserAndChat() {
	loginAndSearchClick();
	search.term("test1");
	sleep(7000);
	// The menu it's not visible then we need to show it
	search.getResultName(SeleniumConstants.USERJID).click();
	// Now we can click the menu
	search.getResultMenu(SeleniumConstants.USERJID).click();
	search.ChatMenuItem().click();
    }
}
