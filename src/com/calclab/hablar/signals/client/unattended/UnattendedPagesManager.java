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
import com.calclab.hablar.signals.client.unattended.UnattendedPagesChangedEvent.ChangeType;

/**
 * A registry of unattended pages. It listen to events and tracks which chat are
 * unattended
 * 
 */
public class UnattendedPagesManager {

    private final HablarEventBus eventBus;
    private final HashSet<Page<?>> unattendedChatPages;

    public UnattendedPagesManager(final HablarEventBus hablarEventBus) {
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
		    final Page<?> page = event.getPage();
		    final Visibility visibility = page.getVisibility();
		    if (visibility != Visibility.focused && unattendedChatPages.add(page)) {
			eventBus.fireEvent(new UnattendedPagesChangedEvent(ChangeType.added, page));
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
	    eventBus.fireEvent(new UnattendedPagesChangedEvent(ChangeType.removed, page));
	}
    }
}
