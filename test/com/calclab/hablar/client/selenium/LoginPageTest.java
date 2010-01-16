package com.calclab.hablar.client.selenium;

import org.openqa.selenium.WebElement;

import com.calclab.hablar.client.login.LoginPage;

public class LoginPageTest extends AbstractPageTest {

    private final WebElement login;
    private final WebElement passwd;
    private final WebElement button;

    public LoginPageTest(final WebTester tester) {
	super(tester, LoginPage.ID);
	login = getById("LoginPage-uri");
	passwd = getById("LoginPage-passwd");
	button = getById("LoginPage-button");
    }

    public void as(final String username, final String password) {
	login.clear();
	passwd.clear();
	login.sendKeys(username);
	passwd.sendKeys(password);
	button.submit();
    }

}
