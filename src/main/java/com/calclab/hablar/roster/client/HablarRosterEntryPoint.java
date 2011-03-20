package com.calclab.hablar.roster.client;

import com.calclab.hablar.roster.client.groups.RosterGroupPresenter;
import com.calclab.hablar.roster.client.selection.DoubleListRosterItemSelector;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

public class HablarRosterEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	final RosterMessages messages = (RosterMessages) GWT.create(RosterMessages.class);
	HablarRoster.setMessages(messages);
	RosterBasicActions.setMessages(messages);
	RosterGroupPresenter.setMessages(messages);
	DoubleListRosterItemSelector.setMessages(messages);
    }

}
