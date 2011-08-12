package com.calclab.hablar.signals.client;

import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.xep.storage.client.PrivateStorageManager;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.browser.BrowserFocusHandler;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.signals.client.browserfocus.BrowserFocusManager;
import com.calclab.hablar.signals.client.notifications.BrowserPopupHablarNotifier;
import com.calclab.hablar.signals.client.notifications.JGrowlHablarNotifier;
import com.calclab.hablar.signals.client.notifications.NotificationManager;
import com.calclab.hablar.signals.client.preferences.SignalsPreferencesPresenter;
import com.calclab.hablar.signals.client.preferences.SignalsPreferencesWidget;
import com.calclab.hablar.signals.client.unattended.UnattendedPagesManager;
import com.calclab.hablar.signals.client.unattended.UnattendedPresenter;
import com.calclab.hablar.user.client.UserContainer;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HasText;

/**
 * Install the signals module into Hablar
 */
public class HablarSignals {

	public HablarSignals(final Hablar hablar, final XmppSession session, final PrivateStorageManager storageManager) {
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

		final UnattendedPagesManager manager = new UnattendedPagesManager(eventBus, BrowserFocusHandler.getInstance());
		new BrowserFocusManager(eventBus, manager, BrowserFocusHandler.getInstance());
		new UnattendedPresenter(eventBus, preferences, manager, titleDisplay);
		final NotificationManager notificationManager = new NotificationManager(eventBus, preferences);

		notificationManager.addNotifier((BrowserPopupHablarNotifier) GWT.create(BrowserPopupHablarNotifier.class), true);
		notificationManager.addNotifier((JGrowlHablarNotifier) GWT.create(JGrowlHablarNotifier.class), true);

		final SignalsPreferencesPresenter preferencesPage = new SignalsPreferencesPresenter(session, storageManager, eventBus, preferences,
				new SignalsPreferencesWidget(), notificationManager);
		hablar.addPage(preferencesPage, UserContainer.ROL);
	}

}
