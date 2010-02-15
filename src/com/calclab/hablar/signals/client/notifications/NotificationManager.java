package com.calclab.hablar.signals.client.notifications;

import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.events.UserMessageChangedEvent;
import com.calclab.hablar.core.client.page.events.UserMessageChangedHandler;

public class NotificationManager {

    public NotificationManager(final HablarEventBus eventBus, final HablarNotifier notifier) {

	eventBus.addHandler(UserMessageChangedEvent.TYPE, new UserMessageChangedHandler() {
	    @Override
	    public void onUserMessageChanged(final UserMessageChangedEvent event) {
		notifier.show(event.getUserMessage());
	    }
	});

    }
}
