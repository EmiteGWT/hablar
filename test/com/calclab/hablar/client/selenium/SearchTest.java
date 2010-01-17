package com.calclab.hablar.client.selenium;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.calclab.hablar.client.i18n.Msg;

public class SearchTest extends AbstractSeleniumTest {
    private LoginPageObject login;
    private RosterPageObject roster;
    private SearchPageObject search;

    @AfterClass
    public void AfterAll() {
	ctx.getWebHelper().close();
    }

    @BeforeMethod
    public void before() {
	ctx.getWebHelper().home();

	final ElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 7);
	login = new LoginPageObject();
	roster = new RosterPageObject();
	search = new SearchPageObject();
	PageFactory.initElements(factory, login);
	PageFactory.initElements(factory, roster);
	PageFactory.initElements(factory, search);
    }

    @Test(groups = { "functest" })
    public void testBasicSearchWithNoResult() {
	login.signInDefUser();
	roster.Header().click();
	roster.SearchIcon().click();
	roster.Header().click();
	search.Header().click();
	search.term("Something difficult to search");
	// Plural for zero it's not working
	search.waitForResult(Msg.RESULTS_FOR);
	search.term("test1");
	search.waitForResult(Msg.RESULTS_FOR);
    }
}
