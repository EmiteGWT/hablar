package com.calclab.hablar.signals.client;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.suco.testing.events.MockedListener;

public class ChatSignalsLogicTest {

    private ChatSignalsLogic logic;
    private MockedListener<Set<PageView>> listener;
    private PageView chat1;
    private PageView chat2;
    private PageView chat3;

    @Test
    public void basicUnnatteded() {
	logic.onChatOpened(chat1);
	logic.onChatOpened(chat2);
	logic.onChatOpened(chat3);
	logic.onNewMsg(chat1);
	logic.onNewMsg(chat1);
	assertTrue(listener.isCalled(1));
	final Set<PageView> set = listener.getValue(0);
	assertEquals(1, set.size());
	assertTrue(set.contains(chat1));
    }

    @Before
    public void before() {
	logic = new ChatSignalsLogic();
	listener = new MockedListener<Set<PageView>>();
	logic.addChatUnattended(listener);
	chat1 = Mockito.mock(PageView.class);
	chat2 = Mockito.mock(PageView.class);
	chat3 = Mockito.mock(PageView.class);
    }

    @Test
    public void testThereTalking() {
	logic.onChatOpened(chat1);
	logic.onChatOpened(chat2);
	logic.onChatOpened(chat3);
	logic.onNewMsg(chat1);
	logic.onNewMsg(chat2);
	logic.onNewMsg(chat1);
	logic.onNewMsg(chat2);
	logic.onNewMsg(chat3);
	logic.onNewMsg(chat3);
	assertTrue(listener.isCalled(2));
	final Set<PageView> set = listener.getValue(0);
	assertEquals(2, set.size());
	assertTrue(set.contains(chat1));
	assertTrue(set.contains(chat2));
	assertFalse(set.contains(chat3));
    }

    @Test
    public void testThereTalkingAndChanging() {
	logic.onChatOpened(chat1);
	logic.onChatOpened(chat2);
	logic.onChatOpened(chat3);
	logic.onNewMsg(chat1);
	logic.onNewMsg(chat2);
	logic.onNewMsg(chat1);
	logic.onNewMsg(chat2);
	logic.onChatOpened(chat2);
	logic.onNewMsg(chat3);
	logic.onNewMsg(chat3);
	logic.onNewMsg(chat2);
	assertTrue(listener.isCalled(4));
	final Set<PageView> set = listener.getValue(0);
	assertEquals(2, set.size());
	assertTrue(set.contains(chat1));
	assertTrue(set.contains(chat3));
	assertFalse(set.contains(chat2));
    }

}
