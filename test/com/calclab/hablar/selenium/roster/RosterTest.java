package com.calclab.hablar.selenium.roster;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.calclab.hablar.selenium.login.LoginPageObject;
import com.calclab.hablar.selenium.search.SearchPageObject;
import com.calclab.hablar.selenium.tools.HablarSeleniumTest;
import com.calclab.hablar.selenium.tools.SeleniumConstants;
import com.calclab.suco.client.Suco;

public class RosterTest extends HablarSeleniumTest {
    private LoginPageObject login;
    private RosterPageObject roster;
    private SearchPageObject search;

    @BeforeClass
    public void beforeClass() {
	login = Suco.get(LoginPageObject.class);
	roster = Suco.get(RosterPageObject.class);
	search = Suco.get(SearchPageObject.class);
    }

    @Test
    public void testRosteritemMenu() {
	loginAndSearchClick();
	search.getTerm().clear();
	search.getTerm().sendKeys("test");
	search.getTerm().sendKeys("\n");
	// final RenderedWebElement resultName =
	// search.getResultName(SeleniumConstants.USERJID);
	// final RenderedWebElement resultMenu =
	// search.getResultMenu(SeleniumConstants.USERJID);
    }

    private void loginAndSearchClick() {
	login.signInDefUser();
	login.assertIsConnectedAs(SeleniumConstants.USERNODE);
	roster.Header().click();
	roster.SearchIcon().click();
    }

}
