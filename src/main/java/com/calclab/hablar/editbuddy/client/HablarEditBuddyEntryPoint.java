package com.calclab.hablar.editbuddy.client;

import com.calclab.hablar.editbuddy.client.ui.EditBuddyWidget;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarEditBuddyEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	final EditBuddyMessages messages = (EditBuddyMessages) GWT.create(EditBuddyMessages.class);
	HablarEditBuddy.setMessages(messages);
	EditBuddyWidget.setMessages(messages);
    }

}
