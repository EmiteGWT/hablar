package com.calclab.hablar.roster.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface RosterMessages extends Messages {
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

    @DefaultMessage("All contacts")
    String allContactsGroupName();

    @DefaultMessage("Start chat")
    String startChatAction();

    @DefaultMessage("Click here to chat with {0}")
    String clickToChatTooltip(String user);

    @DefaultMessage("Select \"Start chat\" in the menu to chat with {0}")
    String startChatTooltip(String user);

    @DefaultMessage("Remove contact")
    String removeContactAction();

    @DefaultMessage("Remove from this group")
    String removeFromGroupAction();

    @DefaultMessage("Delete this group")
    String deleteGroupAction();

    @DefaultMessage("Available users")
    String availableUsersLabelText();

    @DefaultMessage("Selected users")
    String selectedUsersLabelText();

    @DefaultMessage("Select all the users")
    String selectAllTooltip();

    @DefaultMessage("Select the highlighted users")
    String selectSomeTooltip();

    @DefaultMessage("Remove all the users from the selection")
    String deselectAllTooltip();

    @DefaultMessage("Remove the highlighted users from the selection")
    String deselectSomeTooltip();
}
