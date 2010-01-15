package com.calclab.hablar.client.selenium;

import org.openqa.selenium.WebElement;

public interface WebTester {

    void close();

    WebElement getById(String id);

    void home();
}
