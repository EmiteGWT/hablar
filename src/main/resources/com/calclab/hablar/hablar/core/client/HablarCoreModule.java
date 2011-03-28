package com.calclab.hablar.core.client;

import com.calclab.hablar.core.client.browser.BrowserFocusHandler;
import com.calclab.hablar.core.client.ui.prompt.ConfirmWidget;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarCoreModule implements EntryPoint {

    @Override
    public void onModuleLoad() {
	// We will instantiate the BrowserFocusHandler singleton so that it
	// starts tracking focus events as soon as possible.
	BrowserFocusHandler.getInstance();
	final CoreMessages messages = GWT.create(CoreMessages.class);
	ConfirmWidget.setMessages(messages);
    }
}
