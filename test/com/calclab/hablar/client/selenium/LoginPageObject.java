package com.calclab.hablar.client.selenium;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.client.i18n.Msg;
import com.calclab.hablar.client.login.LoginPage;

public class LoginPageObject extends AbstractPageObject {
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

    public void assertIsConnected() {
	waitFor(header, Msg.CONNECTED_AS);
    }

    public void assertIsDisconnected() {
	waitFor(header, Msg.DISCONNECTED);
    }

    public RenderedWebElement getHeader() {
	return header;
    }

    public RenderedWebElement Header() {
	return getHeader();
    }

    public void signIn(final String username, final String password) {
	header.click();
	login.clear();
	passwd.clear();
	assertIsDisconnected();
	login.sendKeys(username);
	passwd.sendKeys(password);
	button.click();
    }

    public void signInDefUser() {
	signIn(SeleniumConstants.USER, SeleniumConstants.PASSWD);
    }

    public void signOut() {
	header.click();
	waitFor(button, Msg.SIGN_OUT);
	button.click();
	assertIsDisconnected();
    }
}
