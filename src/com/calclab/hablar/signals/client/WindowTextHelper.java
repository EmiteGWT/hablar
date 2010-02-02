package com.calclab.hablar.signals.client;

public class WindowTextHelper {
    public static String updateTitle(final String title, final String message) {
	final String extracted = title.replaceFirst("(\\(.*\\))([\\s])?(.*)", "$3");
	return (message != null && !message.isEmpty() ? "(" + message + ") " : "") + extracted;
    }

}
