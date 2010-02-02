package com.calclab.hablar.signals.client;

import java.util.HashSet;
import java.util.Set;

import com.calclab.hablar.basic.client.HablarEventBus;
import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;
import com.calclab.hablar.basic.client.ui.page.events.UserMessageEvent;
import com.calclab.hablar.basic.client.ui.page.events.UserMessageHandler;
import com.calclab.hablar.basic.client.ui.pages.events.PageClosedEvent;
import com.calclab.hablar.basic.client.ui.pages.events.PageClosedHandler;
import com.calclab.hablar.basic.client.ui.pages.events.PageOpenedEvent;
import com.calclab.hablar.basic.client.ui.pages.events.PageOpenedHandler;
import com.calclab.hablar.chat.client.ui.ChatPageWidget;

/**
 * A registry of unattended chat pages. It listen to events and tracks which
 * chat are unattended
 * 
 */
public class UnattendedChatPages {
    private final Set<PageView> unattendedChatPages;

    private final HablarEventBus hablarEventBus;

    public UnattendedChatPages(HablarEventBus hablarEventBus) {
	this.hablarEventBus = hablarEventBus;
	unattendedChatPages = new HashSet<PageView>();
	bind();
    }

    public int getSize() {
	return unattendedChatPages.size();
    }

    public void onChatClosed(final PageView chatPage) {
	if (unattendedChatPages.remove(chatPage)) {
	    hablarEventBus.fireEvent(new UnattendedChatsChangedEvent(this));
	}
    }

    public void onChatOpened(final PageView chatPage) {
	if (chatPage.getVisibility() == Visibility.focused && unattendedChatPages.remove(chatPage)) {
	    hablarEventBus.fireEvent(new UnattendedChatsChangedEvent(this));
	}
    }

    public void onNewMsg(final PageView chatPage) {
	if (chatPage.getVisibility() != Visibility.focused && unattendedChatPages.add(chatPage)) {
	    hablarEventBus.fireEvent(new UnattendedChatsChangedEvent(this));
	}
    }

    private void bind() {
	hablarEventBus.addHandler(UserMessageEvent.TYPE, new UserMessageHandler() {
	    @Override
	    public void onUserMessage(UserMessageEvent event) {
		PageView page = event.getPage().getView();
		if (isChatPage(page)) {
		    onNewMsg(page);
		}
	    }
	});

	hablarEventBus.addHandler(PageClosedEvent.TYPE, new PageClosedHandler() {
	    @Override
	    public void onPageClosed(PageView page) {
		if (isChatPage(page)) {
		    onChatClosed(page);
		}
	    }
	});

	hablarEventBus.addHandler(PageOpenedEvent.TYPE, new PageOpenedHandler() {
	    @Override
	    public void onPageOpened(PageView page) {
		if (isChatPage(page)) {
		    onChatOpened(page);
		}
	    }
	});

    }

    private boolean isChatPage(final PageView page) {
	return page.getPageType() == ChatPageWidget.TYPE;
    }

}
