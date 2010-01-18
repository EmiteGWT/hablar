package com.calclab.hablar.client.selenium;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.calclab.suco.client.Suco;

public class LoginTest extends AbstractSeleniumTest {
    private LoginPageObject login;

    @BeforeClass
    public void beforeClass() {
	login = Suco.get(LoginPageObject.class);
    }

    @Test(dataProvider = "correctlogin")
    public void severalsSignInSingOut(final String user, final String passwd) {
	login.signIn(user, passwd);
	login.assertIsConnected();
	login.signOut();
	login.assertIsDisconnected();
	login.signIn(user, passwd);
	login.assertIsConnected();
	login.signOut();
	login.assertIsDisconnected();
    }

    @Test(dataProvider = "correctlogin")
    public void signIn(final String user, final String passwd) {
	login.signIn(user, passwd);
	login.assertIsConnected();
    }

    @Test(dataProvider = "incorrectlogin")
    public void signInIncorrectPasswd(final String user, final String passwd) {
	login.signIn(user, passwd);
	login.assertIsDisconnected();
    }
}
