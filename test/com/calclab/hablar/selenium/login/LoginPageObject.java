package com.calclab.hablar.selenium.login;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.selenium.PageObject;
import com.calclab.hablar.selenium.tools.SeleniumConstants;

public class LoginPageObject extends PageObject {
    @FindBy(id = "gwt-debug-AccordionHeaderWidget-Login-1")
    private RenderedWebElement header;
    @FindBy(id = "gwt-debug-LoginWidget-user")
    private RenderedWebElement user;
    @FindBy(id = "gwt-debug-LoginWidget-password")
    private RenderedWebElement passwd;
    @FindBy(id = "gwt-debug-LoginWidget-button")
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
	user.clear();
	passwd.clear();
	assertIsDisconnected();
	user.sendKeys(username);
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
