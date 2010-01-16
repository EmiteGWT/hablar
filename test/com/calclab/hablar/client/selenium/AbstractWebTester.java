package com.calclab.hablar.client.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.thoughtworks.selenium.Selenium;

public abstract class AbstractWebTester implements WebTester {
    private static FirefoxDriver driver;
    private final String baseUrl;
    private final Selenium selenium;

    public AbstractWebTester(final String baseUrl) {
	// http://code.google.com/p/selenium/wiki/FirefoxDriver
	// System.getProperties().put("webdriver.firefox.useExisting", "true");
	// System.setProperty("webdriver.firefox.useExisting", "true");

	this.baseUrl = baseUrl;
	// This is the profile of your browser (in you use OOPHM you must
	// install the GWT plugin in the "selenium" profile
	// See:
	// http://code.google.com/intl/es-ES/webtoolkit/doc/latest/DevGuideTestingRemoteTesting.html#Firefox_Profile
	driver = new FirefoxDriver("selenium");

	// About Webdriver or Selenium APIs: Not clear what to use, See:
	// http://groups.google.com/group/webdriver/browse_frm/thread/2c86604006234012/ff2806873921efd6?lnk=gst&q=timeout#ff2806873921efd6
	selenium = new WebDriverBackedSelenium(driver, baseUrl);
	selenium.setTimeout("5000");
    }

    public void close() {
	selenium.close();
    }

    public WebElement getById(final String id) {
	return driver.findElement(By.id("gwt-debug-" + id));
    }

    public void home() {
	assert baseUrl != null;
	driver.get(baseUrl);
    }

    public void wait(final int milliseconds) {
	try {
	    Thread.sleep(milliseconds);
	} catch (final InterruptedException e) {
	    Logger.error("Exception in wait method", e);
	}
    }

}