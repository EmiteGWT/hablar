package com.calclab.hablar.selenium.login;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.calclab.hablar.selenium.HablarSeleniumTest;
import com.calclab.suco.client.Suco;

public class LoginSeleniumTests extends HablarSeleniumTest {
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

    @Test
    public void signIn() {
	login.signIn("test1@localhost", "test1");
	login.assertIsConnectedAs("test1");
    }

    @Test
    public void signInIncorrectPasswd() {
	login.signIn("nouser", "nopassword");
	login.assertIsDisconnected();
    }
}
