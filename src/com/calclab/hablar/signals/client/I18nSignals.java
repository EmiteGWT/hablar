package com.calclab.hablar.signals.client;

public class I18nSignals {

    public static SignalMessages t;

    public static void set(final SignalMessages t) {
	I18nSignals.t = t;
    }

    public static SignalMessages i18n() {
	return t;
    }
}
