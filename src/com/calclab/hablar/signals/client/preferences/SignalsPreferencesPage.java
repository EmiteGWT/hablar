package com.calclab.hablar.signals.client.preferences;

import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.signals.client.SignalPreferences;
import com.calclab.hablar.user.client.EditorPage;

public class SignalsPreferencesPage extends PagePresenter<SignalsPreferencesDisplay> implements
	EditorPage<SignalsPreferencesDisplay> {

    public static final String TYPE = "SignalsPreferences";
    private final SignalPreferences preferences;

    public SignalsPreferencesPage(final HablarEventBus eventBus, final SignalPreferences preferences,
	    final SignalsPreferencesDisplay display) {
	super(TYPE, eventBus, display);
	this.preferences = preferences;
    }

    @Override
    public void saveData() {
	preferences.titleSignals = display.getTitleSignals().getValue();
	preferences.incomingNotifications = display.getIncomingNotifications().getValue();
	preferences.rosterNotifications = display.getRosterNotifications().getValue();
    }

    @Override
    public void showData() {
	display.getTitleSignals().setValue(preferences.titleSignals);
	display.getIncomingNotifications().setValue(preferences.incomingNotifications);
	display.getRosterNotifications().setValue(preferences.rosterNotifications);
    }
}
