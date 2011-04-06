package com.calclab.hablar.group.client;

import com.calclab.hablar.group.client.manage.ManageGroupPresenter;
import com.calclab.hablar.group.client.manage.ManageGroupWidget;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarGroupEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	final GroupMessages messages = (GroupMessages) GWT.create(GroupMessages.class);
	HablarGroup.setMessages(messages);
	ManageGroupWidget.setMessages(messages);
	ManageGroupPresenter.setMessages(messages);
    }

}
