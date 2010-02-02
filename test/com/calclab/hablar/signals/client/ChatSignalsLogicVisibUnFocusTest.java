package com.calclab.hablar.signals.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;
import com.calclab.hablar.chat.client.EmiteTester;
import com.calclab.suco.testing.events.MockedListener;

public class ChatSignalsLogicVisibUnFocusTest {

    private ChatSignalsLogic logic;
    private MockedListener<Set<PageView>> chatsUnattendedListener;
    private PageView chat1;
    private PageView chat2;
    private PageView chat3;
    private MockedListener<PageView> newUnattendedChatListener;
    private EmiteTester tester;

    @Before
    public void before() {
	tester = new EmiteTester();
	logic = new ChatSignalsLogic(tester.eventBus);
	chatsUnattendedListener = new MockedListener<Set<PageView>>();
	newUnattendedChatListener = new MockedListener<PageView>();
	logic.addChatsUnattendedChanged(chatsUnattendedListener);
	logic.addNewUnattendedChat(newUnattendedChatListener);
	chat1 = Mockito.mock(PageView.class);
	chat2 = Mockito.mock(PageView.class);
	chat3 = Mockito.mock(PageView.class);
	mockVisibility(Visibility.notFocused);
    }

    @Test
    public void testOneUnnatteded() {
	logic.onChatOpened(chat1);
	logic.onNewMsg(chat1);
	logic.onNewMsg(chat1);
	assertEquals(1, chatsUnattendedListener.getCalledTimes());
	assertEquals(1, newUnattendedChatListener.getCalledTimes());
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
	assertEquals(3, newUnattendedChatListener.getCalledTimes());
	assertTrue(newUnattendedChatListener.getValue(0).equals(chat1));
	assertTrue(newUnattendedChatListener.getValue(1).equals(chat2));
	assertEquals(3, chatsUnattendedListener.getCalledTimes());
	final Set<PageView> set = chatsUnattendedListener.getValue(2);
	assertEquals(3, set.size());
	assertTrue(set.contains(chat1));
	assertTrue(set.contains(chat2));
	assertTrue(set.contains(chat3));
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
	assertEquals(3, newUnattendedChatListener.getCalledTimes());
	assertTrue(newUnattendedChatListener.getValue(0).equals(chat1));
	assertTrue(newUnattendedChatListener.getValue(1).equals(chat2));
	assertTrue(newUnattendedChatListener.getValue(2).equals(chat3));
	assertEquals(3, chatsUnattendedListener.getCalledTimes());
	final Set<PageView> set = chatsUnattendedListener.getValue(2);
	assertEquals(3, set.size());
	assertTrue(set.contains(chat1));
	assertTrue(set.contains(chat3));
	assertTrue(set.contains(chat2));
    }

    @Test
    public void testTwoUnnatteded() {
	logic.onChatOpened(chat1);
	logic.onChatOpened(chat2);
	logic.onChatOpened(chat3);
	logic.onNewMsg(chat1);
	logic.onNewMsg(chat1);
	assertEquals(1, newUnattendedChatListener.getCalledTimes());
	assertTrue(newUnattendedChatListener.getValue(0).equals(chat1));
	assertEquals(1, chatsUnattendedListener.getCalledTimes());
	final Set<PageView> set = chatsUnattendedListener.getValue(0);
	assertEquals(1, set.size());
	assertTrue(set.contains(chat1));
    }

    @Test
    public void whenOpenningAndClosingTwoChatsShouldNotifyCorrectly() {
	logic.onChatOpened(chat1);
	logic.onChatOpened(chat2);
	logic.onNewMsg(chat1);
	logic.onNewMsg(chat1);
	logic.onNewMsg(chat2);
	logic.onNewMsg(chat2);
	logic.onChatClosed(chat2);
	logic.onChatClosed(chat1);
	logic.onChatOpened(chat1);
	assertEquals(2, newUnattendedChatListener.getCalledTimes());
	assertTrue(newUnattendedChatListener.getValue(0).equals(chat1));
	assertEquals(4, chatsUnattendedListener.getCalledTimes());
	final Set<PageView> set = chatsUnattendedListener.getValue(3);
	assertEquals(0, set.size());
    }

    private void mockVisibility(final Visibility visibility) {
	Mockito.when(chat1.getVisibility()).thenReturn(visibility);
	Mockito.when(chat2.getVisibility()).thenReturn(visibility);
	Mockito.when(chat3.getVisibility()).thenReturn(visibility);
    }

}
