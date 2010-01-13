package com.calclab.hablar.client.chat;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.calclab.emite.im.client.chat.Chat;
import com.calclab.hablar.client.chat.ChatManagerLogic.ChatPageFactory;
import com.calclab.hablar.client.ui.page.Page.Visibility;
import com.calclab.hablar.client.ui.pages.Pages;

public class ChatManagerLogicTest {

    private Pages pages;
    private EmiteTester tester;
    private ArrayList<ChatView> views;

    @Before
    public void before() {
	views = new ArrayList<ChatView>();
	tester = new EmiteTester();
	pages = mock(Pages.class);
	new ChatManagerLogic(new ChatConfig(), pages, new ChatPageFactory() {
	    @Override
	    public ChatView create(Chat chat) {
		ChatView view = mock(ChatView.class);
		views.add(view);
		return view;
	    }
	});
    }

    @Test
    public void shouldAddAClosedChatViewWhenChatCreated() {
	tester.chatManager.fireChatCreated(new ChatTester("me@host", "you@host"));
	verify(pages).add(views.get(0), Visibility.closed);
    }

    @Test
    public void shouldOpenChatViewWhenOpenAChat() {
	ChatTester chat = new ChatTester("me@host", "you@host");
	tester.chatManager.fireChatCreated(chat);
	tester.chatManager.fireChatOpened(chat);
	verify(pages).open(views.get(0));
    }
}
