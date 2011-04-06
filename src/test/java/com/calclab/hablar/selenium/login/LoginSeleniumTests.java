package com.calclab.hablar.selenium.login;

import org.testng.annotations.Test;

import com.calclab.hablar.selenium.HablarSeleniumTest;

public class LoginSeleniumTests extends HablarSeleniumTest {

    @Test(dataProvider = "correctlogin")
    public void severalsSignInSingOut(final String user, final String passwd, final String userNode) {
	login.signIn(user, passwd);
	login.assertIsConnectedAs(userNode);
	login.logout();
	login.assertIsDisconnected();
	login.signIn(user, passwd);
	login.assertIsConnectedAs(userNode);
	login.logout();
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
