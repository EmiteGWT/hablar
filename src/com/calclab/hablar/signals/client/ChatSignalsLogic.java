package com.calclab.hablar.signals.client;

import java.util.HashSet;
import java.util.Set;

import com.calclab.hablar.basic.client.ui.EventBus;
import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;
import com.calclab.hablar.basic.client.ui.page.events.UserMessageEvent;
import com.calclab.hablar.basic.client.ui.page.events.UserMessageHandler;
import com.calclab.hablar.basic.client.ui.pages.events.PageClosedEvent;
import com.calclab.hablar.basic.client.ui.pages.events.PageClosedHandler;
import com.calclab.hablar.basic.client.ui.pages.events.PageOpenedEvent;
import com.calclab.hablar.basic.client.ui.pages.events.PageOpenedHandler;
import com.calclab.hablar.chat.client.ui.ChatPageWidget;
import com.calclab.suco.client.events.Event;
import com.calclab.suco.client.events.Listener;

public class ChatSignalsLogic {
    protected static void ifChat(final PageView page, final Listener<PageView> listener) {
	final String pageType = page.getPageType();
	if (pageType == ChatPageWidget.TYPE) {
	    listener.onEvent(page);
	}
    }
    private final Set<PageView> set;
    private final Event<Set<PageView>> chatsUnattendedChanged;

    private final Event<PageView> newUnattendedChat;
    private final EventBus eventBus;

    public ChatSignalsLogic(EventBus eventBus) {
	this.eventBus = eventBus;
	this.newUnattendedChat = new Event<PageView>("newUnattendedChat");
	this.chatsUnattendedChanged = new Event<Set<PageView>>("chatsUnattendedChanged");
	set = new HashSet<PageView>();
	bind();
    }

    public void addChatsUnattendedChanged(final Listener<Set<PageView>> listener) {
	chatsUnattendedChanged.add(listener);
    }

    public void addNewUnattendedChat(final Listener<PageView> listener) {
	newUnattendedChat.add(listener);
    }

    public void onChatClosed(final PageView chatPage) {
	if (set.remove(chatPage)) {
	    fire();
	}
    }

    public void onChatOpened(final PageView chatPage) {
	if (chatPage.getVisibility() == Visibility.focused && set.remove(chatPage)) {
	    fire();
	}
    }

    public void onNewMsg(final PageView chatPage) {
	if (chatPage.getVisibility() != Visibility.focused && set.add(chatPage)) {
	    fire();
	    // This chat it's new
	    newUnattendedChat.fire(chatPage);
	}
    }

    private void bind() {
	final Listener<PageView> statusMessageChgListener = new Listener<PageView>() {
	    @Override
	    public void onEvent(final PageView chatPage) {
		onNewMsg(chatPage);
	    }
	};

	final Listener<PageView> onPageOpened = new Listener<PageView>() {
	    @Override
	    public void onEvent(final PageView chatPage) {
		onChatOpened(chatPage);
	    }
	};

	final Listener<PageView> onPageClose = new Listener<PageView>() {
	    @Override
	    public void onEvent(final PageView chatPage) {
		onChatClosed(chatPage);
	    }
	};

	eventBus.addHandler(UserMessageEvent.TYPE, new UserMessageHandler() {
	    @Override
	    public void onUserMessage(UserMessageEvent event) {
		ifChat(event.getPage().getView(), statusMessageChgListener);
	    }
	});

	eventBus.addHandler(PageClosedEvent.TYPE, new PageClosedHandler() {
	    @Override
	    public void onPageClosed(PageView page) {
		ifChat(page, onPageClose);
	    }
	});

	eventBus.addHandler(PageOpenedEvent.TYPE, new PageOpenedHandler() {
	    @Override
	    public void onPageOpened(PageView page) {
		ifChat(page, onPageOpened);
	    }
	});

    }

    private void fire() {
	chatsUnattendedChanged.fire(set);
    }

}
