package com.calclab.hablar.selenium.tools;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.calclab.hablar.basic.client.ui.utils.DebugId;

public class DebugTest {

    @Test
    public void before() {
	assertEquals("prefix-john-example", DebugId.getFromJid("prefix-", "john@example/resource"));
    }
}
