package com.calclab.hablar.client.selenium;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.pagefactory.ElementLocatorFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginNGTest extends AbstractSeleniumTest {
    private LoginPageObject login;

    @AfterClass
    public void AfterAll() {
	ctx.getWebHelper().close();
    }

    @BeforeMethod
    public void before() {
	ctx.getWebHelper().home();

	// Maybe use here Guice?
	final ElementLocatorFactory factory = new AjaxElementLocatorFactory(driver, 7);
	login = new LoginPageObject();
	PageFactory.initElements(factory, login);
    }

    @Test
    public void signIn() {
	login.signIn("test1@localhost", "test1");
    }

    @Test
    public void signInSingOut() {
	login.signIn("test1@localhost", "test1");
	login.signOut();
	login.signIn("test1@localhost", "test1");
	login.signOut();
    }

}
