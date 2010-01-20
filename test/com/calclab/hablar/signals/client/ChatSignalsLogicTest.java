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
    private MockedListener<Set<PageView>> chatsUnattendedListener;
    private PageView chat1;
    private PageView chat2;
    private PageView chat3;
    private MockedListener<PageView> newUnattendedChatListener;

    @Before
    public void before() {
	logic = new ChatSignalsLogic();
	chatsUnattendedListener = new MockedListener<Set<PageView>>();
	newUnattendedChatListener = new MockedListener<PageView>();
	logic.addChatsUnattendedChanged(chatsUnattendedListener);
	logic.addNewUnattendedChat(newUnattendedChatListener);
	chat1 = Mockito.mock(PageView.class);
	chat2 = Mockito.mock(PageView.class);
	chat3 = Mockito.mock(PageView.class);
    }

    @Test
    public void testOneUnnatteded() {
	logic.onChatOpened(chat1);
	logic.onNewMsg(chat1);
	logic.onNewMsg(chat1);
	assertTrue(chatsUnattendedListener.isCalled(0));
	assertTrue(newUnattendedChatListener.isCalled(0));
    }

    @Test
    public void testOneUnnattededFirstNewMsgEvent() {
	logic.onNewMsg(chat1);
	logic.onNewMsg(chat1);
	logic.onChatOpened(chat1);
	assertTrue(newUnattendedChatListener.isCalled(1));
	assertTrue(newUnattendedChatListener.getValue(0).equals(chat1));
	assertTrue(chatsUnattendedListener.isCalled(2));
	final Set<PageView> set = chatsUnattendedListener.getValue(1);
	assertEquals(0, set.size());
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
	assertTrue(newUnattendedChatListener.isCalled(2));
	assertTrue(newUnattendedChatListener.getValue(0).equals(chat1));
	assertTrue(newUnattendedChatListener.getValue(1).equals(chat2));
	assertTrue(chatsUnattendedListener.isCalled(2));
	final Set<PageView> set = chatsUnattendedListener.getValue(1);
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
	assertTrue(newUnattendedChatListener.isCalled(3));
	assertTrue(newUnattendedChatListener.getValue(0).equals(chat1));
	assertTrue(newUnattendedChatListener.getValue(1).equals(chat2));
	assertTrue(newUnattendedChatListener.getValue(2).equals(chat3));
	assertTrue(chatsUnattendedListener.isCalled(4));
	final Set<PageView> set = chatsUnattendedListener.getValue(3);
	assertEquals(2, set.size());
	assertTrue(set.contains(chat1));
	assertTrue(set.contains(chat3));
	assertFalse(set.contains(chat2));
    }

    @Test
    public void testTwoUnnatteded() {
	logic.onChatOpened(chat1);
	logic.onChatOpened(chat2);
	logic.onChatOpened(chat3);
	logic.onNewMsg(chat1);
	logic.onNewMsg(chat1);
	assertTrue(newUnattendedChatListener.isCalled(1));
	assertTrue(newUnattendedChatListener.getValue(0).equals(chat1));
	assertTrue(chatsUnattendedListener.isCalled(1));
	final Set<PageView> set = chatsUnattendedListener.getValue(0);
	assertEquals(1, set.size());
	assertTrue(set.contains(chat1));
    }

}
