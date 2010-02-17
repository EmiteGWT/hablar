package com.calclab.hablar.signals.client;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.signals.client.notifications.JGrowlHablarNotifier;
import com.calclab.hablar.signals.client.notifications.NotificationManager;
import com.calclab.hablar.signals.client.preferences.SignalsPreferencesPage;
import com.calclab.hablar.signals.client.preferences.SignalsPreferencesWidget;
import com.calclab.hablar.signals.client.unattended.UnattendedChatPages;
import com.calclab.hablar.user.client.UserContainer;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasText;

public class HablarSignals implements EntryPoint {

    private static SignalMessages signalMessages;

    public static SignalMessages i18n() {
	return signalMessages;
    }

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
	final SignalPreferences preferences = new SignalPreferences();

	new UnattendedChatPages(eventBus);
	new WindowTitlePresenter(eventBus, titleDisplay);
	new NotificationManager(eventBus, preferences, new JGrowlHablarNotifier());

	final SignalsPreferencesPage preferencesPage = new SignalsPreferencesPage(eventBus, preferences,
		new SignalsPreferencesWidget());
	hablar.addPage(preferencesPage, UserContainer.ROL);
    }

    public static void install(final HablarWidget widget) {
	install(widget.getHablar());
    }

    public static void setMessages(final SignalMessages messages) {
	signalMessages = messages;
    }

    @Override
    public void onModuleLoad() {
	setMessages((SignalMessages) GWT.create(SignalMessages.class));
    }

}
