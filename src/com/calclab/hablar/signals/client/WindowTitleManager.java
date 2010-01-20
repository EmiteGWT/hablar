package com.calclab.hablar.signals.client;

import com.google.gwt.user.client.Window;

public class WindowTitleManager {

    static String process(final String currentTitle, final String newMsg) {
	final String extracted = currentTitle.replaceFirst("(\\(.*\\))([\\s])?(.*)", "$3");
	return (newMsg != null && !newMsg.isEmpty() ? "(" + newMsg + ") " : "") + extracted;
    }

    public void addPrefix(final String prefix) {
	Window.setTitle(process(Window.getTitle(), prefix));
    }
}
