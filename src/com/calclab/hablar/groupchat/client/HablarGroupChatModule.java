package com.calclab.hablar.groupchat.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarGroupChatModule implements EntryPoint {

    @Override
    public void onModuleLoad() {
	HablarGroupChat.setMessages((GroupChatMessages) GWT.create(GroupChatMessages.class));
    }

}
