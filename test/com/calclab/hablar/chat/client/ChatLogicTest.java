package com.calclab.hablar.chat.client;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.calclab.emite.core.client.xmpp.stanzas.Message;
import com.calclab.emite.im.client.chat.Chat.State;
import com.calclab.hablar.chat.client.ui.ChatMessageFormatter;
import com.calclab.hablar.chat.client.ui.ChatPageLogic;
import com.calclab.hablar.chat.client.ui.ChatPageView;
import com.calclab.hablar.chat.client.ui.ChatPageView.MessageType;

public class ChatLogicTest {

    private ChatPageView view;
    private ChatTester chat;
    private ChatPageLogic logic;

    @Before
    public void before() {
	view = mock(ChatPageView.class);
	chat = new ChatTester("friend@host", "me@host");
	logic = new ChatPageLogic(chat, view);
    }

    @Test
    public void shouldHideTextBoxWhenChatLocked() {
	chat.setState(State.locked);
	verify(view).setControlsVisible(false);
    }

    @Test
    public void shouldNotShowEmptyMessages() {
	logic.onTalk("");
	verify(view, times(0)).showMessage(anyString(), anyString(), (MessageType) any());
    }

    @Test
    public void shouldShowFormattedIncommingMessages() {
	String msg = "hello http://simple.com";
	chat.receive(new Message(msg));
	verify(view).showMessage("friend", ChatMessageFormatter.format(msg), MessageType.incoming);
    }

    @Test
    public void shouldShowFormattedOutcomingMessages() {
	String msg = "hello too! http://simple.com";
	logic.onTalk(msg);
	verify(view).showMessage("me", ChatMessageFormatter.format(msg), MessageType.sent);
    }
}
