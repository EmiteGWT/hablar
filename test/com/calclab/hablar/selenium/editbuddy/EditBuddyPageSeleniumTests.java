package com.calclab.hablar.selenium.editbuddy;

import org.openqa.selenium.RenderedWebElement;
import org.testng.annotations.Test;

import com.calclab.hablar.selenium.HablarSeleniumTest;

public class EditBuddyPageSeleniumTests extends HablarSeleniumTest {

    @Test
    public void shouldEditBuddy() {
	login("test1");
	RenderedWebElement itemMenu = roster.getItemMenu("test1");

    }
}
