package com.calclab.hablar.login.client;

import com.calclab.emite.browser.client.PageAssist;

public class LoginConfig {
    public static LoginConfig getFromMeta() {
	LoginConfig config = new LoginConfig();
	config.userName = PageAssist.getMeta("hablar.user");
	config.password = PageAssist.getMeta("hablar.password");

	return config;
    }

    public String password;
    public String userName;
}
