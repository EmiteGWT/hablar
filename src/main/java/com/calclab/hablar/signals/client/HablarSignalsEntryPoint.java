package com.calclab.hablar.signals.client;

import com.calclab.hablar.signals.client.preferences.SignalsPreferencesWidget;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarSignalsEntryPoint implements EntryPoint {
    @Override
    public void onModuleLoad() {
	final SignalMessages messages = (SignalMessages) GWT.create(SignalMessages.class);
	HablarSignals.setMessages(messages);
	SignalsPreferencesWidget.setMessages(messages);
    }
}
