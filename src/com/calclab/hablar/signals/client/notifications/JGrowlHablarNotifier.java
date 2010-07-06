package com.calclab.hablar.signals.client.notifications;

import static com.calclab.hablar.signals.client.I18nSignals.i18n;

import com.google.gwt.core.client.GWT;

public class JGrowlHablarNotifier implements HablarNotifier {

    private final boolean isJGrowlPresent;

    public JGrowlHablarNotifier() {
	isJGrowlPresent = isJGrowlPresent();
    }

    @Override
    public void show(final String userMessage, final String messageType) {
	if (isJGrowlPresent) {
	    showJGrowl(userMessage);
	} else {
	    GWT.log("JGrowl is not present, message: " + userMessage);
	}
    }

    private native boolean isJGrowlPresent() /*-{
        return ($wnd.jQuery != undefined && $wnd.jQuery.jGrowl != undefined);
    }-*/;

    private native void showJGrowl(final String userMessage) /*-{
        $wnd.jQuery.jGrowl(userMessage);
    }-*/;

    @Override
    public String getDisplayName() {
	return i18n().jGrowlNotifierDisplayName();
    }

    @Override
    public String getId() {
	return "jgrowlNotifier";
    }

}
