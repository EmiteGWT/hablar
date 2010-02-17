package com.calclab.hablar.signals.client.notifications;

import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.events.UserMessageChangedEvent;
import com.calclab.hablar.core.client.page.events.UserMessageChangedHandler;

public class NotificationManager {

    private final NotificationPreferences preferences;

    public NotificationManager(final HablarEventBus eventBus, final NotificationPreferences preferences,
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

    private boolean isVisibleMessage(final String pageType) {
	return true;
    }
}
