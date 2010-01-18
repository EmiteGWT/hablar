package com.calclab.hablar.client.selenium;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.calclab.suco.client.Suco;

public class SearchTest extends AbstractSeleniumTest {
    private LoginPageObject login;
    private RosterPageObject roster;
    private SearchPageObject search;
    private I18nHelper i18n;

    @BeforeClass
    public void beforeClass() {
	login = Suco.get(LoginPageObject.class);
	roster = Suco.get(RosterPageObject.class);
	search = Suco.get(SearchPageObject.class);
	i18n = Suco.get(I18nHelper.class);
    }

    @Test(enabled = false)
    // Disabled while we fix the plural in I18nHelper
    public void testBasicSearch() {
	loginAndSearchClick();
	search.term("test1");
	search.waitForResult(i18n.get("searchResultsFor", "test1", 1));
    }

    @Test()
    public void testBasicSearchNoResult() {
	loginAndSearchClick();
	search.term("Something difficult to search");
	// zero it's plural in English
	search.waitForResult(i18n.get("searchResultsFor", "Something difficult to search", 0));
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
