package com.calclab.hablar.selenium.login;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.support.FindBy;

import com.calclab.emite.core.client.packet.TextUtils;
import com.calclab.hablar.login.client.LoginMessages;
import com.calclab.hablar.selenium.PageObject;
import com.calclab.hablar.selenium.tools.I18nHelper;
import com.calclab.hablar.selenium.tools.SeleniumConstants;

public class LoginPageObject extends PageObject {
    @FindBy(id = "gwt-debug-HeaderWidget-Login-1")
    private RenderedWebElement header;
    @FindBy(id = "gwt-debug-LoginWidget-user")
    private RenderedWebElement user;
    @FindBy(id = "gwt-debug-LoginWidget-password")
    private RenderedWebElement passwd;
    @FindBy(id = "gwt-debug-LoginWidget-button")
    private RenderedWebElement button;
    private final I18nHelper i18n;

    public LoginPageObject() {
	i18n = new I18nHelper(LoginMessages.class);
    }

    public void assertIsConnectedAs(final String user) {
	waitFor(header, TextUtils.ellipsis(i18n.get("connectedAs", user), 10));
    }

    public void assertIsDisconnected() {
	waitFor(header, TextUtils.ellipsis(i18n.get("disconnected"), 10));
    }

    public RenderedWebElement getHeader() {
	return header;
    }

    public RenderedWebElement Header() {
	return getHeader();
    }

    public void logout() {
	header.click();
	waitFor(button, i18n.get("logout"));
	button.click();
	assertIsDisconnected();
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

}
