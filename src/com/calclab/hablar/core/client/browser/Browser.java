package com.calclab.hablar.core.client.browser;

import com.google.gwt.user.client.Window;

/**
 * A simple abstraction to hide browser implementation details and make testing
 * possible
 */
public class Browser {
    private static Browser instance = new Browser();

    public boolean confirm(String message) {
	return Window.confirm(message);
    }

    public static Browser getInstance() {
	return instance;
    }

    public static void setInstance(Browser instance) {
	Browser.instance = instance;
    }
}
