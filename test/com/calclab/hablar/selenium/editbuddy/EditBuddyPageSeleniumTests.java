package com.calclab.hablar.selenium.editbuddy;

import org.testng.annotations.Test;

import com.calclab.hablar.selenium.roster.AbstractRosterTests;

public class EditBuddyPageSeleniumTests extends AbstractRosterTests {

    @Test
    public void shouldEditRosterItem() {
	addSeleniumBuddy();
	login();
	roster.getHeader().click();
	roster.getItemMenu("", "selenium@localhost").click();
	roster.getEditBuddyAction().click();
	editBuddy.getNewNickName().clear();
	editBuddy.getNewNickName().sendKeys("othernick");
	editBuddy.getSave().click();
	logout();
	removeSeleniumBuddy();
    }

}
