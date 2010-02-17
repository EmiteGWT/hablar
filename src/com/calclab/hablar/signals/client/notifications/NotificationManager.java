package com.calclab.hablar.signals.client.notifications;

import com.calclab.hablar.chat.client.ui.ChatPresenter;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.events.UserMessageChangedEvent;
import com.calclab.hablar.core.client.page.events.UserMessageChangedHandler;
import com.calclab.hablar.rooms.client.room.RoomPresenter;
import com.calclab.hablar.roster.client.page.RosterPresenter;
import com.calclab.hablar.signals.client.SignalPreferences;

public class NotificationManager {

    private final SignalPreferences preferences;

    public NotificationManager(final HablarEventBus eventBus, final SignalPreferences preferences,
	    final HablarNotifier notifier) {

	this.preferences = preferences;
	eventBus.addHandler(UserMessageChangedEvent.TYPE, new UserMessageChangedHandler() {
	    @Override
	    public void onUserMessageChanged(final UserMessageChangedEvent event) {
		if (isVisibleMessage(event.getPageType())) {
		    notifier.show(event.getUserMessage());
		}
	    }
	});
    }

    private boolean isChat(final String pageType) {
	return ChatPresenter.TYPE.equals(pageType) || RoomPresenter.TYPE.equals(pageType);
    }

    private boolean isVisibleMessage(final String pageType) {
	if (preferences.incomingNotifications && isChat(pageType)) {
	    return true;
	} else if (preferences.rosterNotifications && RosterPresenter.TYPE.equals(pageType)) {
	    return true;
	}
	return false;
    }
}
