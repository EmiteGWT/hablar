package com.calclab.hablar.signals.client.notifications;

import com.calclab.hablar.chat.client.ui.PairChatPresenter;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.events.UserMessageEvent;
import com.calclab.hablar.core.client.page.events.UserMessageHandler;
import com.calclab.hablar.rooms.client.room.RoomPresenter;
import com.calclab.hablar.roster.client.page.RosterPresenter;
import com.calclab.hablar.signals.client.SignalPreferences;

public class NotificationManager {

    private final SignalPreferences preferences;

    public NotificationManager(final HablarEventBus eventBus, final SignalPreferences preferences,
	    final HablarNotifier notifier) {

	this.preferences = preferences;
	eventBus.addHandler(UserMessageEvent.TYPE, new UserMessageHandler() {
	    @Override
	    public void onUserMessage(final UserMessageEvent event) {
		if (isVisibleMessage(event.getMessageType())) {
		    notifier.show(event.getUserMessage());
		}
	    }
	});
    }

    private boolean isVisibleMessage(final String messageType) {
	if (messageType.equals(PairChatPresenter.CHAT_MESSAGE) || messageType.equals(RoomPresenter.ROOM_MESSAGE)) {
	    return preferences.incomingMessages;
	} else if (messageType.equals(RosterPresenter.ROSTER_MESSAGE)) {
	    return preferences.rosterNotifications;
	}
	return false;
    }
}
