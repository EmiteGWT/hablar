package com.calclab.hablar.chat.client;

import com.calclab.hablar.chat.client.ui.ChatWidget;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarChatModule implements EntryPoint {

    @Override
    public void onModuleLoad() {
	final ChatMessages messages = (ChatMessages) GWT.create(ChatMessages.class);
	HablarChat.setMessages(messages);
	ChatWidget.setMessages(messages);
    }

}
