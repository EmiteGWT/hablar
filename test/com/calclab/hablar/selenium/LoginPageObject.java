package com.calclab.hablar.selenium;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.basic.client.login.LoginPage;
import com.calclab.hablar.basic.client.ui.utils.DebugId;

public class LoginPageObject extends AbstractPageObject {
    @FindBy(id = DebugId.PRE + LoginPage.ID)
    private RenderedWebElement header;
    @FindBy(id = DebugId.PRE + LoginPage.URI)
    private RenderedWebElement login;
    @FindBy(id = DebugId.PRE + LoginPage.PASSWD)
    private RenderedWebElement passwd;
    @FindBy(id = DebugId.PRE + LoginPage.BTN)
    private RenderedWebElement button;

    public LoginPageObject() {
    }

    public void assertIsConnectedAs(final String user) {
	waitFor(header, i18n.get("connectedAs", user));
    }

    public void assertIsDisconnected() {
	waitFor(header, i18n.get("disconnected"));
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
	signIn(SeleniumConstants.USERJID, SeleniumConstants.PASSWD);
    }

    public void signOut() {
	header.click();
	waitFor(button, i18n.get("logout"));
	button.click();
	assertIsDisconnected();
    }
}
