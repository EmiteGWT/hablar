package com.calclab.hablar.openchat.client;

import com.calclab.hablar.openchat.client.ui.OpenChatWidget;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarOpenChatModule implements EntryPoint {

    @Override
    public void onModuleLoad() {
	final OpenChatMessages messages = (OpenChatMessages) GWT.create(OpenChatMessages.class);
	HablarOpenChat.setMessages(messages);
	OpenChatWidget.setMessages(messages);
    }

}
