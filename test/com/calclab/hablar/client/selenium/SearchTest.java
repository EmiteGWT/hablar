package com.calclab.hablar.client.selenium;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.calclab.hablar.client.i18n.Msg;
import com.calclab.suco.client.Suco;

public class SearchTest extends AbstractSeleniumTest {
    private LoginPageObject login;
    private RosterPageObject roster;
    private SearchPageObject search;

    @BeforeClass
    public void beforeClass() {
	login = Suco.get(LoginPageObject.class);
	roster = Suco.get(RosterPageObject.class);
	search = Suco.get(SearchPageObject.class);
    }

    @Test()
    public void testBasicSearch() {
	loginAndSearchClick();
	search.term("test1");
	search.waitForResult(Msg.RESULTS_FOR);
    }

    @Test()
    public void testBasicSearchNoResult() {
	loginAndSearchClick();
	search.term("Something difficult to search");
	// zero it's plural in English
	search.waitForResult(Msg.RESULTS_FOR);
    }

    @Test()
    public void testBasicSearchs() {
	loginAndSearchClick();
	search.term("*");
	search.waitForResult(Msg.RESULTS_FOR);
    }

    private void loginAndSearchClick() {
	login.signInDefUser();
	login.assertIsConnected();
	roster.Header().click();
	roster.SearchIcon().click();
	// Only to test accordion
	roster.Header().click();
	search.Header().click();
    }
}
