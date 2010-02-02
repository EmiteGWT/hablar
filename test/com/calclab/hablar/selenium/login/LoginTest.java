package com.calclab.hablar.selenium.login;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.calclab.hablar.selenium.tools.HablarSeleniumTest;
import com.calclab.suco.client.Suco;

public class LoginTest extends HablarSeleniumTest {
    private LoginPageObject login;

    @BeforeClass
    public void beforeClass() {
	login = Suco.get(LoginPageObject.class);
    }

    @Test(dataProvider = "correctlogin")
    public void severalsSignInSingOut(final String user, final String passwd, final String userNode) {
	login.signIn(user, passwd);
	login.assertIsConnectedAs(userNode);
	login.signOut();
	login.assertIsDisconnected();
	login.signIn(user, passwd);
	login.assertIsConnectedAs(userNode);
	login.signOut();
	login.assertIsDisconnected();
    }

    @Test(dataProvider = "correctlogin")
    public void signIn(final String user, final String passwd, final String usernode) {
	login.signIn(user, passwd);
	login.assertIsConnectedAs(usernode);
    }

    @Test(dataProvider = "incorrectlogin")
    public void signInIncorrectPasswd(final String user, final String passwd) {
	login.signIn(user, passwd);
	login.assertIsDisconnected();
    }
}
