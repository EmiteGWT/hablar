package com.calclab.hablar.client.selenium;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeClass;

public class AbstractSeleniumTest {

    protected FirefoxDriver driver;
    protected WebContext ctx;

    @BeforeClass
    public void beforeAll() {
	// FIXME: If we decide to use TestNG get this out of here and clean-up
	driver = new FirefoxDriver("selenium");
	ctx = new WebContext(new GenericWebTester(driver,
		"http://localhost:8888/Hablar.html?gwt.codesvr=127.0.1.1:9997"));
    }
}
