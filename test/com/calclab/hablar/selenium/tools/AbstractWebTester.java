package com.calclab.hablar.selenium.tools;

import java.awt.Point;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;

import com.google.gwt.user.client.ui.UIObject;
import com.thoughtworks.selenium.Selenium;

public abstract class AbstractWebTester {
    private final WebDriver driver;
    private final String baseUrl;
    private final Selenium selenium;

    public AbstractWebTester(final WebDriver driver, final String baseUrl) {
	this.driver = driver;
	this.baseUrl = baseUrl;

	selenium = new WebDriverBackedSelenium(driver, baseUrl);
	selenium.setTimeout("5000");
    }

    public void close() {
	driver.close();
    }

    public WebElement getById(final String id) {
	return driver.findElement(By.id(UIObject.DEBUG_ID_PREFIX + id));
    }

    public void home() {
	assert baseUrl != null;
	driver.get(baseUrl);
    }

    public boolean isTextPresent(final String text) {
	return selenium.isTextPresent(text);
    }

    public void moveMouseAt(final Point point) {
	selenium.mouseMoveAt(String.valueOf(point.getX()), String.valueOf(point.getY()));
    }

}