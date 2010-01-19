package com.calclab.hablar.basic.client.selenium;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class I18nHelperTest {

    private I18nHelper i18n;

    @Before
    public void before() {
	i18n = new I18nHelper();
    }

    @Test
    public void testOnePlural() {
	assertEquals("Results for «test1»: One user found.", i18n.get("searchResultsFor", "test1", 1));
    }

    @Test
    public void testSimpleArg() {
	assertEquals("Connected as test1", i18n.get("connectedAs", "test1"));
    }

    @Test
    public void testSimpleSeveralArgs() {
	assertEquals("Results for «test1»: 2 users found.", i18n.get("searchResultsFor", "test1", 2));
    }

}
