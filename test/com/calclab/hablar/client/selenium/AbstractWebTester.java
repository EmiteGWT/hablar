package com.calclab.hablar.client.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;

import com.google.gwt.user.client.ui.UIObject;
import com.thoughtworks.selenium.Selenium;

public abstract class AbstractWebTester implements WebTester {
    private final WebDriver driver;
    private final String baseUrl;
    private final Selenium selenium;

    public AbstractWebTester(final WebDriver driver, final String baseUrl) {
	this.driver = driver;
	this.baseUrl = baseUrl;

	// About Webdriver or Selenium APIs: Not clear what to use, See:
	// http://groups.google.com/group/webdriver/browse_frm/thread/2c86604006234012/ff2806873921efd6?lnk=gst&q=timeout#ff2806873921efd6
	selenium = new WebDriverBackedSelenium(driver, baseUrl);
	selenium.setTimeout("5000");
    }

    public void close() {
	selenium.close();
    }

    public WebElement getById(final String id) {
	return driver.findElement(By.id(UIObject.DEBUG_ID_PREFIX + id));
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