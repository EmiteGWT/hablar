package com.calclab.hablar.signals.client;

/**
 * Provides internationalised messages for the {@link HablarSignals} module
 */
public class I18nSignals {

    public static SignalMessages t;

    /**
     * Sets the {@link SignalMessages} object containing the internationalised messages
     * @param t the messages object
     */
    public static void set(final SignalMessages t) {
	I18nSignals.t = t;
    }

    /**
     * Gets the {@link SignalMessages} object containing the internationalised messages
     * @return the SignalMessages object containing the internationalised messages
     */
    public static SignalMessages i18n() {
	return t;
    }
}
