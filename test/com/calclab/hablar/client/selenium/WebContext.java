package com.calclab.hablar.client.selenium;


public class WebContext {
    private final AbstractWebTester webHelper;

    public WebContext(final AbstractWebTester webHelper) {
	this.webHelper = webHelper;
    }

    public AbstractWebTester getWebHelper() {
	return webHelper;
    }

}
