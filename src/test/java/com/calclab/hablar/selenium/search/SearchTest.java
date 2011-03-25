package com.calclab.hablar.selenium.search;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.calclab.hablar.search.client.SearchMessages;
import com.calclab.hablar.selenium.HablarSeleniumTest;
import com.calclab.hablar.selenium.tools.I18nHelper;
import com.calclab.hablar.selenium.tools.SeleniumConstants;

public class SearchTest extends HablarSeleniumTest {
    private I18nHelper i18n;

    @BeforeClass
    public void beforeClass() {
	i18n = new I18nHelper(SearchMessages.class);
    }

    @Test
    public void loginSearchTestUserAndChat() {
	loginAndSearchClick();
	search.getTerm().clear();
	search.getTerm().sendKeys("test1\n");
	search.waitForResultMenu(SeleniumConstants.USERJID);
	search.getResultMenu(SeleniumConstants.USERJID).click();
	search.getChatAction().click();
	search.getChat(SeleniumConstants.USERJID).click();
	logout();
    }

    @Test
    public void shouldAddBuddyFromSearch() {
	login();
	final String jid = "selenium@localhost";
	roster.getHeader().click();
	search.getAction().click();
	search.getTerm().clear();
	search.getTerm().sendKeys("selenium\n");
	search.waitForResultMenu(jid);
	search.getResultMenu(jid).click();
	if (webtester.isElementPresent(SearchPageObject.GWT_DEBUG_SEARCH_LOGIC_REMOVE_ITEM)
		&& search.getRemoveBuddyAction().isDisplayed()) {
	    search.getRemoveBuddyAction().click();
	    sleep(1000);
	}
	search.getResultMenu(jid).click();
	search.waitForMenuAddAction();
	search.getAddBuddyAction().click();
	sleep(1000);
	search.getResultMenu(jid).click();
	search.waitForMenuRemoveAction();
	search.getResultMenu(jid).click();
	search.getRemoveBuddyAction().click();
	logout();
    }

    @Test
    public void shouldShowSearchResults() {
	loginAndSearchClick();
	search.getTerm().clear();
	search.getTerm().sendKeys("test1\n");
	search.waitForResultMenu(SeleniumConstants.USERJID);
	search.waitForResult(i18n.get("searchResultsFor", "test1", 1));
	logout();
    }

    @Test
    public void testBasicSearchNoResult() {
	loginAndSearchClick();
	search.getTerm().clear();
	search.getTerm().sendKeys("IMPOSSIBLE XXXXZZZÑÑÑ\n");
	sleep(5000);
	// zero it's plural in English
	search.waitForResult(i18n.get("searchResultsFor", "IMPOSSIBLE XXXXZZZÑÑÑ", 0));
	logout();
    }

    private void loginAndSearchClick() {
	login.signInDefUser();
	login.assertIsConnectedAs(SeleniumConstants.USERNODE);
	roster.getHeader().click();
	search.getAction().click();
	// Only to test accordion
	roster.getHeader().click();
	search.getHeader().click();
    }
}
