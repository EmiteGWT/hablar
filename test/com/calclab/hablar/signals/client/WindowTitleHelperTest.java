package com.calclab.hablar.signals.client;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WindowTitleHelperTest {

    @Test
    public void basicTitleSubs() {
	assertEquals("(1) lalala", WindowTitleHelper.process("lalala", "1"));
	assertEquals("(something new) lalala", WindowTitleHelper.process("lalala", "something new"));
	assertEquals("(1) lalala", WindowTitleHelper.process("(1) lalala", "1"));
	assertEquals("(1) lalala", WindowTitleHelper.process("(2 asldfal) lalala", "1"));
	assertEquals("(something new) lalala", WindowTitleHelper.process("(*) lalala", "something new"));
	assertEquals("lalala", WindowTitleHelper.process("(*) lalala", ""));
	assertEquals("lalala", WindowTitleHelper.process("(*) lalala", null));
	assertEquals("(*) lalala", WindowTitleHelper.process("lalala", "*"));
    }
}
