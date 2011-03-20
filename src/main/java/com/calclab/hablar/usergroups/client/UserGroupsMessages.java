package com.calclab.hablar.usergroups.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface UserGroupsMessages extends Messages {
    @DefaultMessage("Change groups")
    String changeContactGroups();

    @DefaultMessage("Change contact groups")
    String changeContactGroupsTitle();

    @DefaultMessage("Accept")
    String acceptAction();

    @DefaultMessage("Cancel")
    String cancelAction();

    @DefaultMessage("New group")
    String newGroupAction();

    @DefaultMessage("Contact Name:")
    String contactNameLabelText();

    @DefaultMessage("Roster Groups:")
    String rosterGroupsLabelText();
}
