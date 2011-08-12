package com.calclab.hablar.signals.client.notifications;

import com.calclab.hablar.signals.client.SignalMessages;
import com.google.gwt.core.client.GWT;

public class JGrowlHablarNotifier implements HablarNotifier {

	private final boolean isJGrowlPresent;

	public JGrowlHablarNotifier() {
		isJGrowlPresent = isJGrowlPresent();
	}

	@Override
	public String getDisplayName() {
		return SignalMessages.msg.jGrowlNotifierDisplayName();
	}

	@Override
	public String getId() {
		return "jgrowlNotifier";
	}

	//@formatter:off
	private native boolean isJGrowlPresent() /*-{
		return ($wnd.jQuery != undefined && $wnd.jQuery.jGrowl != undefined);
	}-*/;
	//@formatter:on

	@Override
	public void show(final String userMessage, final String messageType) {
		if (isJGrowlPresent) {
			showJGrowl(userMessage);
		} else {
			GWT.log("JGrowl is not present, message: " + userMessage);
		}
	}

	//@formatter:off
	private native void showJGrowl(final String userMessage) /*-{
		$wnd.jQuery.jGrowl(userMessage);
	}-*/;
	//@formatter:on

}
