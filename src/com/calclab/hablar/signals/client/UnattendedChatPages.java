package com.calclab.hablar.signals.client;

import java.util.HashSet;

import com.calclab.hablar.chat.client.ui.ChatPage;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.events.UserMessageChangedEvent;
import com.calclab.hablar.core.client.page.events.UserMessageChangedHandler;
import com.calclab.hablar.core.client.page.events.VisibilityChangedEvent;
import com.calclab.hablar.core.client.page.events.VisibilityChangedHandler;

/**
 * A registry of unattended chat pages. It listen to events and tracks which
 * chat are unattended
 * 
 */
public class UnattendedChatPages {

    private final HablarEventBus eventBus;
    private final HashSet<Page<?>> unattendedChatPages;

    public UnattendedChatPages(HablarEventBus hablarEventBus) {
	this.eventBus = hablarEventBus;
	unattendedChatPages = new HashSet<Page<?>>();
	bind();
    }

    public boolean contains(Page<?> page) {
	return unattendedChatPages.contains(page);
    }

    public int getSize() {
	return unattendedChatPages.size();
    }

    private void bind() {
	eventBus.addHandler(UserMessageChangedEvent.TYPE, new UserMessageChangedHandler() {
	    @Override
	    public void onUserMessageChanged(UserMessageChangedEvent event) {
		Page<?> page = event.getPage();
		if (isChatPage(page)) {
		    Visibility visibility = page.getVisibility();
		    if (visibility != Visibility.focused && unattendedChatPages.add(page)) {
			eventBus.fireEvent(new UnattendedChatsChangedEvent(UnattendedChatPages.this));
		    }
		}
	    }
	});

	eventBus.addHandler(VisibilityChangedEvent.TYPE, new VisibilityChangedHandler() {
	    @Override
	    public void onVisibilityChanged(VisibilityChangedEvent event) {
		Page<?> page = event.getPage();
		if (isChatPage(page)) {
		    onChatVisibilityChanged(page);
		}
	    }

	});

    }

    private final boolean isChatPage(final Page<?> page) {
	return page.getType() == ChatPage.TYPE;
    }

    private void onChatVisibilityChanged(Page<?> page) {
	Visibility visibility = page.getVisibility();
	if (visibility == Visibility.focused && unattendedChatPages.remove(page)) {
	    eventBus.fireEvent(new UnattendedChatsChangedEvent(this));
	} else if (unattendedChatPages.remove(page)) {
	    eventBus.fireEvent(new UnattendedChatsChangedEvent(this));
	}
    }

}
