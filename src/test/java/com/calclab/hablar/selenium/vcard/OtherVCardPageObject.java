package com.calclab.hablar.selenium.vcard;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.selenium.PageObject;

public class OtherVCardPageObject extends PageObject implements VCardPageObject {

    @FindBy(id = "gwt-debug-OtherVCardWidget-name")
    RenderedWebElement name;

    @FindBy(id = "gwt-debug-OtherVCardWidget-nickName")
    RenderedWebElement nickName;

    @FindBy(id = "gwt-debug-OtherVCardWidget-familyName")
    RenderedWebElement familyName;

    @FindBy(id = "gwt-debug-OtherVCardWidget-givenName")
    RenderedWebElement givenName;

    @FindBy(id = "gwt-debug-OtherVCardWidget-middleName")
    RenderedWebElement middleName;

    @FindBy(id = "gwt-debug-OtherVCardWidget-organizationName")
    RenderedWebElement organizationName;

    @FindBy(id = "gwt-debug-OtherVCardWidget-email")
    RenderedWebElement email;

    @FindBy(id = "gwt-debug-OtherVCardWidget-homepage")
    RenderedWebElement homepage;

    public OtherVCardPageObject(final WebDriver webdriver) {
	super(webdriver);
    }

    @Override
    public RenderedWebElement getEmail() {
	return email;
    }

    @Override
    public RenderedWebElement getFamilyName() {
	return familyName;
    }

    @Override
    public RenderedWebElement getGivenName() {
	return givenName;
    }

    @Override
    public RenderedWebElement getHomepage() {
	return homepage;
    }

    @Override
    public RenderedWebElement getMiddleName() {
	return middleName;
    }

    @Override
    public RenderedWebElement getName() {
	return name;
    }

    @Override
    public RenderedWebElement getNickName() {
	return nickName;
    }

    @Override
    public RenderedWebElement getOrganizationName() {
	return organizationName;
    }
}
