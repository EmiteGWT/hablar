package com.calclab.hablar.signals.client;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.signals.client.notifications.JGrowlHablarNotifier;
import com.calclab.hablar.signals.client.notifications.NotificationManager;
import com.calclab.hablar.signals.client.preferences.SignalsPreferencesPresenter;
import com.calclab.hablar.signals.client.preferences.SignalsPreferencesWidget;
import com.calclab.hablar.signals.client.unattended.UnattendedPagesManager;
import com.calclab.hablar.signals.client.unattended.UnattendedPresenter;
import com.calclab.hablar.user.client.UserContainer;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
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
	final SignalPreferences preferences = new SignalPreferences();

	final UnattendedPagesManager manager = new UnattendedPagesManager(eventBus);
	new UnattendedPresenter(eventBus, preferences, manager, titleDisplay);
	new NotificationManager(eventBus, preferences, new JGrowlHablarNotifier());

	final SignalsPreferencesPresenter preferencesPage = new SignalsPreferencesPresenter(eventBus, preferences,
		new SignalsPreferencesWidget());
	hablar.addPage(preferencesPage, UserContainer.ROL);
    }

    public static void install(final HablarWidget widget) {
	install(widget.getHablar());
    }

    @Override
    public void onModuleLoad() {
	SignalMessages messages = (SignalMessages) GWT.create(SignalMessages.class);
	I18nSignals.set(messages);
	SignalsPreferencesWidget.setMessages(messages);
    }

}
