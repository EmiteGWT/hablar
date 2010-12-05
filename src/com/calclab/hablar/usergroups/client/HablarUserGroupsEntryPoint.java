package com.calclab.hablar.usergroups.client;

import com.calclab.hablar.usergroups.client.changegroups.ManageGroupsWidget;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarUserGroupsEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	final UserGroupsMessages messages = GWT.create(UserGroupsMessages.class);
	HablarUserGroups.setMessages(messages);
	ManageGroupsWidget.setMessages(messages);
    }

}
