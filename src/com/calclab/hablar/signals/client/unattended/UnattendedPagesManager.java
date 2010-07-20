package com.calclab.hablar.signals.client.unattended;

import java.util.HashSet;

import com.calclab.hablar.chat.client.ui.PairChatPresenter;
import com.calclab.hablar.core.client.browser.BrowserFocusHandler;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.events.UserMessageEvent;
import com.calclab.hablar.core.client.page.events.UserMessageHandler;
import com.calclab.hablar.core.client.page.events.VisibilityChangedEvent;
import com.calclab.hablar.core.client.page.events.VisibilityChangedHandler;
import com.calclab.hablar.rooms.client.room.RoomPresenter;
import com.calclab.hablar.signals.client.unattended.UnattendedChatsChangedEvent.ChangeType;

/**
 * A registry of unattended pages. It listen to events and tracks which chat are
 * unattended
 * 
 */
public class UnattendedPagesManager {

    private final HablarEventBus eventBus;
    private final HashSet<Page<?>> unattendedChatPages;
    private final BrowserFocusHandler focusManager;

    public UnattendedPagesManager(final HablarEventBus hablarEventBus, BrowserFocusHandler focusManager) {
	eventBus = hablarEventBus;
	this.focusManager = focusManager;
	unattendedChatPages = new HashSet<Page<?>>();
	bind();
    }

    public boolean contains(final Page<?> page) {
	return unattendedChatPages.contains(page);
    }

    public int getSize() {
	return unattendedChatPages.size();
    }

    private void bind() {
	eventBus.addHandler(UserMessageEvent.TYPE, new UserMessageHandler() {
	    @Override
	    public void onUserMessage(final UserMessageEvent event) {
		final String messageType = event.getMessageType();
		if (isChatMessage(messageType)) {
		    final Page<?> page = event.getPage();
		    final Visibility visibility = page.getVisibility();
		    
		    if ((visibility != Visibility.focused || !focusManager.hasFocus()) && unattendedChatPages.add(page)) {
			eventBus.fireEvent(new UnattendedChatsChangedEvent(ChangeType.added, page));
		    }
		}
	    }
	});

	eventBus.addHandler(VisibilityChangedEvent.TYPE, new VisibilityChangedHandler() {
	    @Override
	    public void onVisibilityChanged(final VisibilityChangedEvent event) {
		final Page<?> page = event.getPage();
		if (isChatPage(page.getType())) {
		    onChatVisibilityChanged(page, event.getVisibility());
		}
	    }

	});

    }

    private boolean isChatMessage(final String messageType) {
	return messageType.equals(PairChatPresenter.CHAT_MESSAGE) || messageType.equals(RoomPresenter.ROOM_MESSAGE);
    }

    private boolean isChatPage(final String pageType) {
	return pageType.equals(PairChatPresenter.TYPE) || pageType.equals(RoomPresenter.TYPE);
    }

    private void onChatVisibilityChanged(final Page<?> page, Visibility visibility) {
	if ((visibility == Visibility.focused || visibility == Visibility.hidden) && unattendedChatPages.remove(page)) {
	    eventBus.fireEvent(new UnattendedChatsChangedEvent(ChangeType.removed, page));
	}
    }
}
