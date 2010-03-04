package com.calclab.hablar.selenium.editbuddy;

import org.testng.annotations.Test;

import com.calclab.hablar.selenium.roster.AbstractRosterTests;

public class EditBuddyPageSeleniumTests extends AbstractRosterTests {

    @Test
    public void shouldEditRosterItem() {
	login();
	addSeleniumBuddy();
	roster.getHeader().click();
	roster.getItemMenu("", "selenium@localhost").click();
	roster.getEditBuddyAction().click();
	editBuddy.getNewNickName().clear();
	editBuddy.getNewNickName().sendKeys("othernick");
	editBuddy.getSave().click();
	removeSeleniumBuddy();
	logout();
    }

}
