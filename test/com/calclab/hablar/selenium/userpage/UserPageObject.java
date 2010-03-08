package com.calclab.hablar.selenium.userpage;

import org.openqa.selenium.RenderedWebElement;
import org.openqa.selenium.support.FindBy;

import com.calclab.hablar.selenium.PageObject;
import com.calclab.hablar.selenium.vcard.VCardPageObject;

public class UserPageObject extends PageObject implements VCardPageObject {

    @FindBy(id = "gwt-debug-HeaderWidget-User-1")
    private RenderedWebElement header;

    @FindBy(id = "gwt-debug-PresenceWidget-status")
    private RenderedWebElement status;

    @FindBy(id = "gwt-debug-PresenceWidget-menu")
    private RenderedWebElement menu;

    @FindBy(id = "gwt-debug-SignalsPreferencesWidget-titleSignals-input")
    private RenderedWebElement titleSignals;

    @FindBy(id = "gwt-debug-SignalsPreferencesWidget-incomingNotifications-input")
    private RenderedWebElement incomingNotifications;

    @FindBy(id = "gwt-debug-SignalsPreferencesWidget-rosterNotifications-input")
    private RenderedWebElement rosterNotifications;

    @FindBy(id = "gwt-debug-UserWidget-close")
    private RenderedWebElement close;

    @FindBy(id = "gwt-debug-OwnVCardWidget-name")
    private RenderedWebElement name;

    @FindBy(id = "gwt-debug-OwnVCardWidget-nickName")
    private RenderedWebElement nickName;

    @FindBy(id = "gwt-debug-OwnVCardWidget-familyName")
    private RenderedWebElement familyName;

    @FindBy(id = "gwt-debug-OwnVCardWidget-givenName")
    private RenderedWebElement givenName;

    @FindBy(id = "gwt-debug-OwnVCardWidget-middleName")
    private RenderedWebElement middleName;

    @FindBy(id = "gwt-debug-OwnVCardWidget-organizationName")
    private RenderedWebElement organizationName;

    @FindBy(id = "gwt-debug-OwnVCardWidget-email")
    private RenderedWebElement email;

    @FindBy(id = "gwt-debug-OwnVCardWidget-homepage")
    private RenderedWebElement homepage;

    public RenderedWebElement getClose() {
	return close;
    }

    public RenderedWebElement getEmail() {
	return email;
    }

    public RenderedWebElement getFamilyName() {
	return familyName;
    }

    public RenderedWebElement getGivenName() {
	return givenName;
    }

    public RenderedWebElement getHeader() {
	return header;
    }

    public RenderedWebElement getHomepage() {
	return homepage;
    }

    public RenderedWebElement getIncomingNotifications() {
	return incomingNotifications;
    }

    public RenderedWebElement getMenu() {
	return menu;
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

    public RenderedWebElement getRosterNotifications() {
	return rosterNotifications;
    }

    public RenderedWebElement getStatus() {
	return status;
    }

    public RenderedWebElement getTitleSignals() {
	return titleSignals;
    }

    public void waitForStatus(final String text) {
	waitFor(header, text);
    }

}
