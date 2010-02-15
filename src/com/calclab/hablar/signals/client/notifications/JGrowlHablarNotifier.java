package com.calclab.hablar.signals.client.notifications;

public class JGrowlHablarNotifier implements HablarNotifier {

    private final boolean isJGrowlPresent;

    public JGrowlHablarNotifier() {
	isJGrowlPresent = isJGrowlPresent();
    }

    @Override
    public void show(final String userMessage) {
	if (isJGrowlPresent) {
	    showJGrowl(userMessage);
	}
    }

    private native boolean isJGrowlPresent() /*-{
        return ($wnd.jQuery != undefined && $wnd.jQuery.jGrowl != undefined);
    }-*/;

    private native void showJGrowl(final String userMessage) /*-{
        $wnd.jQuery.jGrowl(userMessage);
    }-*/;

}
