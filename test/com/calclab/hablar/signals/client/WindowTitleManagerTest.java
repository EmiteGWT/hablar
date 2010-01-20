package com.calclab.hablar.signals.client;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WindowTitleManagerTest {

    @Test
    public void basicTitleSubs() {
	assertEquals("(1) lalala", WindowTitleManager.process("lalala", "1"));
	assertEquals("(1) lalala", WindowTitleManager.process("(1) lalala", "1"));
	assertEquals("(1) lalala", WindowTitleManager.process("(2 asldfal) lalala", "1"));
	assertEquals("(something new) lalala", WindowTitleManager.process("(*) lalala", "something new"));
	assertEquals("lalala", WindowTitleManager.process("(*) lalala", ""));
	assertEquals("lalala", WindowTitleManager.process("(*) lalala", null));
    }
}
