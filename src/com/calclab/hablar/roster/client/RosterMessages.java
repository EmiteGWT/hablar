package com.calclab.hablar.roster.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface RosterMessages extends Messages {
    @DefaultMessage("Add to a group")
    String addToToGroupAction();

    @DefaultMessage("Delete group {0} with {1} items. Are you sure?")
    String confirmDeleteGroup(String groupName, String itemCount);

    @DefaultMessage("Contacts")
    @Description("The roster panel title")
    String contacts();

    @DefaultMessage("Group: {0} ({1})")
    String groupName(String groupName, String groupSize);

    @DefaultMessage("User {0} has been added to contacts.")
    String userAdded(String name);

    @DefaultMessage("User {0} has been removed from contacts.")
    String userRemoved(String shortName);

}
