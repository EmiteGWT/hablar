package com.calclab.hablar.selenium.vcard;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.selenium.PageObject;

public class OtherVCardPageObject extends PageObject implements VCardPageObject {

    @FindBy(id = "gwt-debug-OtherVCardWidget-name") RenderedWebElement name;

    @FindBy(id = "gwt-debug-OtherVCardWidget-nickName") RenderedWebElement nickName;

    @FindBy(id = "gwt-debug-OtherVCardWidget-familyName") RenderedWebElement familyName;

    @FindBy(id = "gwt-debug-OtherVCardWidget-givenName") RenderedWebElement givenName;

    @FindBy(id = "gwt-debug-OtherVCardWidget-middleName") RenderedWebElement middleName;

    @FindBy(id = "gwt-debug-OtherVCardWidget-organizationName") RenderedWebElement organizationName;

    @FindBy(id = "gwt-debug-OtherVCardWidget-email") RenderedWebElement email;

    @FindBy(id = "gwt-debug-OtherVCardWidget-homepage") RenderedWebElement homepage;

    public RenderedWebElement getEmail() {
	return email;
    }

    public RenderedWebElement getFamilyName() {
	return familyName;
    }

    public RenderedWebElement getGivenName() {
	return givenName;
    }

    public RenderedWebElement getHomepage() {
	return homepage;
    }

    public RenderedWebElement getMiddleName() {
	return middleName;
    }

    public RenderedWebElement getName() {
	return name;
    }

    public RenderedWebElement getNickName() {
	return nickName;
    }

    public RenderedWebElement getOrganizationName() {
	return organizationName;
    }
}
