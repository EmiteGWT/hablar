package com.calclab.hablar.client.selenium;

import com.allen_sauer.gwt.log.client.Log;

public class Logger {

    public static void error(final String message) {
	error(message, null);
    }

    public static void error(final String message, final Throwable ex) {
	Log.error(message, ex);
    }

    public static void info(final String message) {
	Log.info(message);
    }
}
