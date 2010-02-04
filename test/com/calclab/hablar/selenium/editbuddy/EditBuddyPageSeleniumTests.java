package com.calclab.hablar.selenium.editbuddy;

import org.testng.annotations.Test;

import com.calclab.hablar.selenium.HablarSeleniumTest;

public class EditBuddyPageSeleniumTests extends HablarSeleniumTest {

    @Test
    public void shouldEditBuddy() {
	login("test1@localhost", "test1");
    }
}
