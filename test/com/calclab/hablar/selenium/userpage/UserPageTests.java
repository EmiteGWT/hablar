package com.calclab.hablar.selenium.userpage;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.calclab.hablar.selenium.HablarSeleniumTest;
import com.calclab.hablar.selenium.vcard.VCardPageObject;

public class UserPageTests extends HablarSeleniumTest {

    @Test
    public void shoulChangeStatus() {
	login();
	userPage.getHeader().click();
	userPage.getStatus().sendKeys("Sleeping");
	userPage.getClose().click();
	webtester.isTextPresent("Sleeping");
	logout();
    }

    @Test
    public void shouldSavePrefs() {
	login();
	userPage.getHeader().click();
	userPage.waitFor(userPage.getTitleSignals());
	togglePrefs();
	userPage.getClose().click();
	logout();
	login();
	userPage.getHeader().click();
	userPage.waitFor(userPage.getTitleSignals());
	togglePrefs();
	Assert.assertEquals("on", userPage.getTitleSignals().getValue());
	Assert.assertEquals("on", userPage.getIncomingNotifications().getValue());
	Assert.assertEquals("on", userPage.getRosterNotifications().getValue());
	userPage.getClose().click();
	logout();
    }

    @Test
    public void shouldSaveVCard() {
	login();
	final String value = getTempString();
	userPage.getHeader().click();
	userPage.getName().clear();
	userPage.getFamilyName().clear();
	userPage.getGivenName().clear();
	userPage.getMiddleName().clear();
	userPage.getNickName().clear();
	userPage.getHomepage().clear();
	userPage.getEmail().clear();
	userPage.getOrganizationName().clear();
	userPage.getName().sendKeys(value);
	userPage.getFamilyName().sendKeys(value);
	userPage.getGivenName().sendKeys(value);
	userPage.getMiddleName().sendKeys(value);
	userPage.getNickName().sendKeys(value);
	userPage.getHomepage().sendKeys(value);
	userPage.getEmail().sendKeys(value);
	userPage.getOrganizationName().sendKeys(value);
	userPage.getClose().click();
	logout();
	login();
	userPage.getHeader().click();
	checkVCard(value, userPage);
	userPage.getClose().click();
	addSeleniumBuddy();
	roster.getHeader().click();
	roster.getItemMenu("", "selenium@localhost").click();
	roster.getSeeBuddyVCardAction().click();
	sleep(1000);
	checkVCard(value, otherVCardPage);
	removeSeleniumBuddy();
	logout();
    }

    private void checkVCard(final String value, final VCardPageObject page) {
	Assert.assertEquals(page.getName().getValue(), value);
	Assert.assertEquals(page.getFamilyName().getValue(), value);
	Assert.assertEquals(page.getGivenName().getValue(), value);
	Assert.assertEquals(page.getNickName().getValue(), value);
	Assert.assertEquals(page.getMiddleName().getValue(), value);
	Assert.assertEquals(page.getHomepage().getValue(), value);
	Assert.assertEquals(page.getEmail().getValue(), value);
	Assert.assertEquals(page.getOrganizationName().getValue(), value);
    }

    private void togglePrefs() {
	userPage.getTitleSignals().click();
	userPage.getIncomingNotifications().click();
	userPage.getRosterNotifications().click();
    }
}
