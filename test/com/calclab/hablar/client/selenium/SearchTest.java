package com.calclab.hablar.client.selenium;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.calclab.suco.client.Suco;

public class SearchTest extends AbstractSeleniumTest {
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
    public void testBasicSearchNoResult() {
	loginAndSearchClick();
	search.term("Something difficult to search");
	sleep(5000);
	// zero it's plural in English
	search.waitForResult(i18n.get("searchResultsFor", "Something difficult to search", 0));
    }

    @Test()
    public void testSearchAndChat() {
	loginAndSearchClick();
	search.term("test1");
	sleep(7000);
	// The menu it's not visible then we need to show it
	search.getResultName(SeleniumConstants.USERJID).click();
	// Now we can click the menu
	search.getResultMenu(SeleniumConstants.USERJID).click();
	search.ChatMenuItem().click();
	chat.TalkBox().sendKeys("Hi ;)\n");
	chat.TalkBox().sendKeys("some echo tests\n");
	// Uncomment to don't close the window
	// mustCloseFinally = false;
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
}
