package com.calclab.hablar.usergroups.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface UserGroupsMessages extends Messages {
	public static final UserGroupsMessages msg = GWT.create(UserGroupsMessages.class);

	@DefaultMessage("Change groups")
	String changeContactGroups();

	@DefaultMessage("New group")
	String newGroupAction();

	@DefaultMessage("Contact Name:")
	String contactNameLabelText();

	@DefaultMessage("Roster Groups:")
	String rosterGroupsLabelText();
}
