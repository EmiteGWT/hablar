package com.calclab.hablar.client.selenium;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.client.login.LoginPage;

public class LoginPageObject {
    @FindBy(id = Debug.PRE + LoginPage.ID)
    private RenderedWebElement header;
    @FindBy(id = Debug.PRE + LoginPage.URI)
    private RenderedWebElement login;
    @FindBy(id = Debug.PRE + LoginPage.PASSWD)
    private RenderedWebElement passwd;
    @FindBy(id = Debug.PRE + LoginPage.BTN)
    private RenderedWebElement button;

    public LoginPageObject() {
    }

    public void as(final String username, final String password) {
	login.clear();
	passwd.clear();
	login.sendKeys(username);
	passwd.sendKeys(password);
	button.submit();
    }

    public RenderedWebElement getHeader() {
	return header;
    }

    public RenderedWebElement Header() {
	return getHeader();
    }

}
