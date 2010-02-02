package com.calclab.hablar.selenium.search;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.calclab.hablar.selenium.tools.HablarSeleniumTest;
import com.calclab.hablar.selenium.tools.I18nHelper;
import com.calclab.hablar.selenium.tools.SeleniumConstants;
import com.calclab.suco.client.Suco;

public class SearchTest extends HablarSeleniumTest {
    private I18nHelper i18n;

    @BeforeClass
    public void beforeClass() {
	i18n = Suco.get(I18nHelper.class);
    }

    @Test()
    public void shouldShowSearchResults() {
	loginAndSearchClick();
	search.getTerm().clear();
	search.getTerm().sendKeys("test1\n");
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
	search.getTerm().clear();
	search.getTerm().sendKeys("IMPOSSIBLE XXXXZZZÑÑÑ\n");
	sleep(5000);
	// zero it's plural in English
	search.waitForResult(i18n.get("searchResultsFor", "IMPOSSIBLE XXXXZZZÑÑÑ", 0));
    }

    private void loginAndSearchClick() {
	login.signInDefUser();
	login.assertIsConnectedAs(SeleniumConstants.USERNODE);
	roster.getHeader().click();
	roster.getSearchIcon().click();
	// Only to test accordion
	roster.getHeader().click();
	search.getHeader().click();
    }

    private void loginSearchTestUserAndChat() {
	loginAndSearchClick();
	search.getTerm().clear();
	search.getTerm().sendKeys("test1\n");
	sleep(7000);
	// The menu it's not visible then we need to show it
	search.getResultName(SeleniumConstants.USERJID).click();
	// Now we can click the menu
	search.getResultMenu(SeleniumConstants.USERJID).click();
	search.ChatMenuItem().click();
    }
}
