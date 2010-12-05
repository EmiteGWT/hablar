package com.calclab.hablar.user.client;

import com.calclab.hablar.user.client.presence.PresenceWidget;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarUserEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	final UserMessages messages = (UserMessages) GWT.create(UserMessages.class);
	HablarUser.setMessages(messages);
	PresenceWidget.setMessages(messages);
	UserWidget.setMessages(messages);
    }

}
