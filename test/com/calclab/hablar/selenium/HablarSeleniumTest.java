package com.calclab.hablar.selenium;

import java.awt.Point;

import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;

import com.calclab.suco.client.Suco;

public abstract class HablarSeleniumTest {
    public static boolean mustCloseFinally = true;

    @AfterSuite
    public void AfterSuite() {
	// We try to only open one window for all our selenium tests
	if (mustCloseFinally) {
	    closeBrowser();
	}
    }

    @BeforeMethod
    public void beforeMethod() {
	// This reload the baseUrl and then "starts" the GWT page to test, in
	// every test method
	goHome();
    }

    @BeforeSuite
    public void beforeSuite(final ITestContext context) {
	Suco.install(new SeleniumModule());
    }

    public void closeBrowser() {
	getWebTester().close();
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

    public void goHome() {
	getWebTester().home();
    }

    public void moveMouseAt(final Point point) {
	getWebTester().moveMouseAt(point);
    }

    public void sleep(final int milliseconds) {
	try {
	    Thread.sleep(milliseconds);
	} catch (final InterruptedException e) {
	    Assert.fail("Exception in sleep method", e);
	}
    }

    private GenericWebTester getWebTester() {
	return Suco.get(GenericWebTester.class);
    }

}
