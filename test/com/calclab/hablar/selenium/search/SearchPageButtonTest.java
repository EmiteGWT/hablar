package com.calclab.hablar.selenium.search;

import org.testng.annotations.Test;

import com.calclab.hablar.selenium.HablarSeleniumTest;

public class SearchPageButtonTest extends HablarSeleniumTest {

    @Test
    public void shouldHaveSearchButton() {
	login.signIn("test1@localhost", "test1");
	login.assertIsConnectedAs("test1");
	roster.getHeader().click();
	roster.getSearchIcon().click();
	search.getSearchButton();
    }
}
