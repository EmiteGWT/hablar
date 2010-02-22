package com.calclab.hablar.signals.client.unattended;

import java.util.HashSet;

import com.calclab.hablar.chat.client.ui.ChatPresenter;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.page.PagePresenter.Visibility;
import com.calclab.hablar.core.client.page.events.UserMessageEvent;
import com.calclab.hablar.core.client.page.events.UserMessageHandler;
import com.calclab.hablar.core.client.page.events.VisibilityChangedEvent;
import com.calclab.hablar.core.client.page.events.VisibilityChangedHandler;
import com.calclab.hablar.rooms.client.room.RoomPresenter;

/**
 * A registry of unattended chat pages. It listen to events and tracks which
 * chat are unattended
 * 
 */
public class UnattendedChatPages {

    private final HablarEventBus eventBus;
    private final HashSet<Page<?>> unattendedChatPages;

    public UnattendedChatPages(final HablarEventBus hablarEventBus) {
	eventBus = hablarEventBus;
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
		    Page<?> page = event.getPage();
		    final Visibility visibility = page.getVisibility();
		    if (visibility != Visibility.focused && unattendedChatPages.add(page)) {
			eventBus.fireEvent(new UnattendedChatsChangedEvent(UnattendedChatPages.this));
		    }
		}
	    }
	});

	eventBus.addHandler(VisibilityChangedEvent.TYPE, new VisibilityChangedHandler() {
	    @Override
	    public void onVisibilityChanged(final VisibilityChangedEvent event) {
		final Page<?> page = event.getPage();
		if (isChatPage(page.getType())) {
		    onChatVisibilityChanged(page);
		}
	    }

	});

    }

    private boolean isChatMessage(final String messageType) {
	return messageType.equals(ChatPresenter.CHAT_MESSAGE) || messageType.equals(RoomPresenter.ROOM_MESSAGE);
    }

    private boolean isChatPage(final String pageType) {
	return pageType.equals(ChatPresenter.TYPE) || pageType.equals(RoomPresenter.TYPE);
    }

    private void onChatVisibilityChanged(final Page<?> page) {
	final Visibility visibility = page.getVisibility();
	if (visibility == Visibility.focused && unattendedChatPages.remove(page)) {
	    eventBus.fireEvent(new UnattendedChatsChangedEvent(this));
	} else if (unattendedChatPages.remove(page)) {
	    eventBus.fireEvent(new UnattendedChatsChangedEvent(this));
	}
    }

}
