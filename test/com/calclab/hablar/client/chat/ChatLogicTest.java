package com.calclab.hablar.client.chat;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.calclab.emite.core.client.xmpp.stanzas.Message;
import com.calclab.hablar.client.chat.ChatView.MessageType;

public class ChatLogicTest {

    private ChatView view;
    private ChatTester chat;
    private ChatLogic logic;

    @Before
    public void before() {
	view = mock(ChatView.class);
	chat = new ChatTester("friend@host", "me@host");
	logic = new ChatLogic(chat, view);

    }

    @Test
    public void shouldShowIncommingMessages() {
	chat.receive(new Message("hello"));
	verify(view).showMessage("friend", "hello", MessageType.incoming);
    }

    @Test
    public void shouldShowOutputMessages() {
	logic.onTalk("hello too!");
	verify(view).showMessage("me", "hello too!", MessageType.sent);
    }
}
