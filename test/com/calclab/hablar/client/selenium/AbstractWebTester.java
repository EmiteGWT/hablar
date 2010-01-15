package com.calclab.hablar.client.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public abstract class AbstractWebTester implements WebTester {
    private static FirefoxDriver driver;

    private String initUrl;

    public AbstractWebTester() {
	// http://code.google.com/p/selenium/wiki/FirefoxDriver
	// System.getProperties().put("webdriver.firefox.useExisting", "true");
	// System.setProperty("webdriver.firefox.useExisting", "true");

	// This is the profile of your browser (in you use OOPHM you must
	// install the GWT plugin in the "selenium" profile
	// See:
	// http://code.google.com/intl/es-ES/webtoolkit/doc/latest/DevGuideTestingRemoteTesting.html#Firefox_Profile
	driver = new FirefoxDriver("selenium");
    }

    public void close() {
	driver.close();
    }

    public WebElement getById(final String id) {
	return driver.findElement(By.id("gwt-debug-" + id));
    }

    public void home() {
	// About webdriver with multiple windows:
	// http://groups.google.com/group/webdriver/browse_thread/thread/6d19907f7653e8b1
	// to test
	driver.get(initUrl);
    }

    public void initUrl(final String url) {
	this.initUrl = url;
    }

    public void wait(final int milliseconds) {
	try {
	    Thread.sleep(milliseconds);
	} catch (final InterruptedException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

}