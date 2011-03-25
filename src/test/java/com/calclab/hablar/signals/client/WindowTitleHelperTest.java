package com.calclab.hablar.signals.client;

import static com.calclab.hablar.signals.client.WindowTextHelper.updateTitle;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class WindowTitleHelperTest {

    @Test
    public void basicTitleSubs() {
	assertEquals("(1) lalala", updateTitle("lalala", "1"));
	assertEquals("(something new) lalala", updateTitle("lalala", "something new"));
	assertEquals("(1) lalala", updateTitle("(1) lalala", "1"));
	assertEquals("(1) lalala", updateTitle("(2 asldfal) lalala", "1"));
	assertEquals("(something new) lalala", updateTitle("(*) lalala", "something new"));
	assertEquals("lalala", updateTitle("(*) lalala", ""));
	assertEquals("lalala", updateTitle("(*) lalala", null));
	assertEquals("(*) lalala", updateTitle("lalala", "*"));
    }
}
