package com.calclab.hablar.group.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface GroupMessages extends Messages {

    @DefaultMessage("Create group")
    String createGroupAction();

    @DefaultMessage("Create a new group")
    String createNewGroup();

    @DefaultMessage("Modify group")
    String modifyGroupAction();

    @DefaultMessage("Modify group")
    String modifyGroup();

    @DefaultMessage("Group name:")
    String groupNameLabelText();

    @DefaultMessage("The group name cannot be empty")
    String groupNameEmptyErrorMessage();

    @DefaultMessage("The group must have at least one member")
    String selectionEmptyErrorMessage();

    @DefaultMessage("Users:")
    String usersLabelText();

    @DefaultMessage("Accept")
    String acceptAction();

    @DefaultMessage("Cancel")
    String cancelAction();

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
