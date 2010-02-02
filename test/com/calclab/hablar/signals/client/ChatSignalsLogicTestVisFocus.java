package com.calclab.hablar.signals.client;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;
import com.calclab.hablar.chat.client.EmiteTester;
import com.calclab.suco.testing.events.MockedListener;

public class ChatSignalsLogicTestVisFocus {

    private ChatSignalsLogic logic;
    private MockedListener<Set<PageView>> chatsUnattendedListener;
    private PageView chat1;
    private PageView chat2;
    private PageView chat3;
    private MockedListener<PageView> newUnreadMessagesListener;
    private EmiteTester tester;

    @Before
    public void before() {
	tester = new EmiteTester();
	logic = new ChatSignalsLogic(tester.eventBus);
	chatsUnattendedListener = new MockedListener<Set<PageView>>();
	newUnreadMessagesListener = new MockedListener<PageView>();
	logic.addChatsUnattendedChanged(chatsUnattendedListener);
	logic.addNewUnattendedChat(newUnreadMessagesListener);
	chat1 = Mockito.mock(PageView.class);
	chat2 = Mockito.mock(PageView.class);
	chat3 = Mockito.mock(PageView.class);
    }

    @Test
    public void testOneUnnatteded() {
	mockVisibility(Visibility.focused, Visibility.notFocused, Visibility.notFocused);
	logic.onChatOpened(chat1);
	logic.onNewMsg(chat1);
	logic.onNewMsg(chat1);
	assertEquals(0, chatsUnattendedListener.getCalledTimes());
	assertEquals(0, newUnreadMessagesListener.getCalledTimes());
    }

    @Test
    public void testThereTalking() {
	mockVisibility(Visibility.notFocused, Visibility.notFocused, Visibility.focused);
	logic.onChatOpened(chat1);
	logic.onChatOpened(chat2);
	logic.onChatOpened(chat3);
	logic.onNewMsg(chat1);
	logic.onNewMsg(chat2);
	logic.onNewMsg(chat1);
	logic.onNewMsg(chat2);
	logic.onNewMsg(chat3);
	logic.onNewMsg(chat3);
	assertEquals(2, newUnreadMessagesListener.getCalledTimes());
	assertTrue(newUnreadMessagesListener.getValue(0).equals(chat1));
	assertTrue(newUnreadMessagesListener.getValue(1).equals(chat2));
	assertEquals(2, chatsUnattendedListener.getCalledTimes());
	final Set<PageView> set = chatsUnattendedListener.getValue(1);
	assertEquals(2, set.size());
	assertTrue(set.contains(chat1));
	assertTrue(set.contains(chat2));
	assertFalse(set.contains(chat3));
    }

    @Test
    public void testThereTalkingAndChanging() {
	mockVisibility(Visibility.notFocused, Visibility.notFocused, Visibility.focused);
	logic.onChatOpened(chat1);
	logic.onChatOpened(chat2);
	logic.onChatOpened(chat3);
	logic.onNewMsg(chat1);
	logic.onNewMsg(chat2);
	logic.onNewMsg(chat1);
	logic.onNewMsg(chat2);
	mockVisibility(Visibility.notFocused, Visibility.focused, Visibility.notFocused);
	logic.onChatOpened(chat2);
	logic.onNewMsg(chat3);
	logic.onNewMsg(chat3);
	logic.onNewMsg(chat2);
	assertEquals(3, newUnreadMessagesListener.getCalledTimes());
	assertTrue(newUnreadMessagesListener.getValue(0).equals(chat1));
	assertTrue(newUnreadMessagesListener.getValue(1).equals(chat2));
	assertTrue(newUnreadMessagesListener.getValue(2).equals(chat3));
	assertEquals(4, chatsUnattendedListener.getCalledTimes());
	final Set<PageView> set = chatsUnattendedListener.getValue(3);
	assertEquals(2, set.size());
	assertTrue(set.contains(chat1));
	assertTrue(set.contains(chat3));
	assertFalse(set.contains(chat2));
    }

    @Test
    public void testTwoUnnatteded() {
	mockVisibility(Visibility.notFocused, Visibility.notFocused, Visibility.focused);
	logic.onChatOpened(chat1);
	logic.onChatOpened(chat2);
	logic.onChatOpened(chat3);
	logic.onNewMsg(chat1);
	logic.onNewMsg(chat1);
	assertEquals(1, newUnreadMessagesListener.getCalledTimes());
	assertTrue(newUnreadMessagesListener.getValue(0).equals(chat1));
	assertEquals(1, chatsUnattendedListener.getCalledTimes());
	final Set<PageView> set = chatsUnattendedListener.getValue(0);
	assertEquals(1, set.size());
	assertTrue(set.contains(chat1));
    }

    @Test
    public void whenOpenningAndClosingTwoChatsShouldNotifyCorrectly() {
	mockVisibility(Visibility.notFocused, Visibility.focused, Visibility.notFocused);
	logic.onChatOpened(chat1);
	logic.onChatOpened(chat2);
	logic.onNewMsg(chat1);
	logic.onNewMsg(chat1);
	logic.onNewMsg(chat2);
	logic.onNewMsg(chat2);
	logic.onChatClosed(chat2);
	logic.onChatClosed(chat1);
	logic.onChatOpened(chat1);
	logic.onNewMsg(chat1);
	assertEquals(2, newUnreadMessagesListener.getCalledTimes());
	assertTrue(newUnreadMessagesListener.getValue(0).equals(chat1));
	assertEquals(3, chatsUnattendedListener.getCalledTimes());
	final Set<PageView> set = chatsUnattendedListener.getValue(2);
	assertEquals(1, set.size());
	assertTrue(set.contains(chat1));
    }

    private void mockVisibility(final Visibility vis1, final Visibility vis2, final Visibility vis3) {
	Mockito.when(chat1.getVisibility()).thenReturn(vis1);
	Mockito.when(chat2.getVisibility()).thenReturn(vis2);
	Mockito.when(chat3.getVisibility()).thenReturn(vis3);
    }

}
