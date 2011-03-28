package com.calclab.hablar.clipboard.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarClipboardEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	final ClipboardMessages messages = (ClipboardMessages) GWT.create(ClipboardMessages.class);
	HablarClipboard.setMessages(messages);
	CopyToClipboardWidget.setMessages(messages);
    }
}
