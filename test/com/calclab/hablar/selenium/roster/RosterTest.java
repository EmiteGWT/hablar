package com.calclab.hablar.selenium.roster;

import org.openqa.selenium.RenderedWebElement;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.calclab.hablar.selenium.HablarSeleniumTest;
import com.calclab.hablar.selenium.SeleniumConstants;
import com.calclab.hablar.selenium.login.LoginPageObject;
import com.calclab.hablar.selenium.search.SearchPageObject;
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

    @Test(enabled = false)
    public void testRosteritemMenu() {
	loginAndSearchClick();
	search.term("test1");
	search.term("test1");
	final RenderedWebElement resultName = search.getResultName(SeleniumConstants.USERJID);
	final RenderedWebElement resultMenu = search.getResultMenu(SeleniumConstants.USERJID);
	// This doesn't works
	moveMouseAt(resultName.getLocation());
	resultMenu.click();
	// This Fails because the menu it's not visible (some workaround in
	// SearchTest)
    }

    private void loginAndSearchClick() {
	login.signInDefUser();
	login.assertIsConnectedAs(SeleniumConstants.USERNODE);
	roster.Header().click();
	roster.SearchIcon().click();
    }

}
