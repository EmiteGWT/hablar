package com.calclab.hablar.client.selenium;

import org.openqa.selenium.WebElement;

import com.calclab.hablar.client.login.LoginPage;

public class LoginPageTest extends AbstractWebElement {

    private final WebElement mainPanel;
    private final WebElement login;
    private final WebElement passwd;
    private final WebElement button;

    public LoginPageTest(final WebTester tester) {
	super(tester);
	mainPanel = getById(LoginPage.ID);
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

    public void focus() {
	mainPanel.click();
    }

    public boolean hasHeader(final String header) {
	// FIXME: while defining if we use Junit or not
	try {
	    super.waitForTextRegExp(mainPanel, header);
	    return true;
	} catch (final Exception e) {
	    return false;
	}
    }
}
