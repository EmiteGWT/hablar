package com.calclab.hablar.roster.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface RosterMessages extends Messages {
    @DefaultMessage("All contacts")
    String allContactsGroupName();

    @DefaultMessage("Available users")
    String availableUsersLabelText();

    @DefaultMessage("Click here to chat with {0}")
    String clickItemToChatWithTooltip(String user);

    @DefaultMessage("Click to chat with")
    String clickToChatWith();

    @DefaultMessage("The group ''{0}'' has {1} items. Are you sure you want to delete it?")
    String confirmDeleteGroup(String groupName, String itemCount);

    @DefaultMessage("Delete group ''{0}''")
    String confirmDeleteGroupTitle(String groupName);

    @DefaultMessage("Contacts")
    @Description("The roster panel title")
    String contacts();

    @DefaultMessage("Delete this group")
    String deleteGroupAction();

    @DefaultMessage("Remove all the users from the selection")
    String deselectAllTooltip();

    @DefaultMessage("Remove the highlighted users from the selection")
    String deselectSomeTooltip();

    @DefaultMessage("Group: {0} ({1})")
    String groupName(String groupName, String groupSize);

    @DefaultMessage("Remove contact")
    String removeContactAction();

    @DefaultMessage("Remove from this group")
    String removeFromGroupAction();

    @DefaultMessage("Select all the users")
    String selectAllTooltip();

    @DefaultMessage("Selected users")
    String selectedUsersLabelText();

    @DefaultMessage("Select the highlighted users")
    String selectSomeTooltip();

    @DefaultMessage("Start chat")
    String startChatAction();

    @DefaultMessage("Select \"Start chat\" in the menu to chat with {0}")
    String startChatTooltip(String user);

    @DefaultMessage("User {0} has been added to contacts.")
    String userAdded(String name);

    @DefaultMessage("User {0} has been removed from contacts.")
    String userRemoved(String shortName);

}
