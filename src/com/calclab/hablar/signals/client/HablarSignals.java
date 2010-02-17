package com.calclab.hablar.signals.client;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.signals.client.notifications.JGrowlHablarNotifier;
import com.calclab.hablar.signals.client.notifications.NotificationManager;
import com.calclab.hablar.signals.client.notifications.NotificationPreferences;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasText;

public class HablarSignals implements EntryPoint {

    public static void install(final Hablar hablar) {
	final HablarEventBus eventBus = hablar.getEventBus();
	final HasText titleDisplay = new HasText() {
	    @Override
	    public String getText() {
		return Window.getTitle();
	    }

	    @Override
	    public void setText(final String text) {
		Window.setTitle(text);
	    }
	};

	final NotificationPreferences preferences = new NotificationPreferences();
	new UnattendedChatPages(eventBus);
	new WindowTitlePresenter(eventBus, titleDisplay);
	new NotificationManager(eventBus, preferences, new JGrowlHablarNotifier());
    }

    public static void install(final HablarWidget widget) {
	install(widget.getHablar());
    }

    @Override
    public void onModuleLoad() {
    }

}
