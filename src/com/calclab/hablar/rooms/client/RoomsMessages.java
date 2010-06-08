package com.calclab.hablar.rooms.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface RoomsMessages extends Messages {

    @DefaultMessage("Accept")
    String acceptAction();

    @DefaultMessage("Cancel")
    String cancelAction();

    @DefaultMessage("The group name can''t be empty")
    String emptyGroupChatName();

    @DefaultMessage("groupChat-{0}")
    String groupChatId(int groupChatNumber);

    @DefaultMessage("Group Chat Name:")
    String groupChatNameLabelText();

    @DefaultMessage("''{0}'' has joined the conversation")
    String hasJoined(String nick);

    @DefaultMessage("''{0}'' has left the conversation")
    String hasLeft(String nick);

    @DefaultMessage("At group chat ''{0}'': {1}")
    String incommingAdminMessage(String groupChatName, String body);

    @DefaultMessage("At group chat ''{0}'', {1} says «{2}»")
    String incommingMessage(String groupChatName, String user, String msg);

    @DefaultMessage("Invitation message:")
    String invitationMessageLabelText();

    @DefaultMessage("Invite more people to this group chat")
    String invitePeopleToGroupChat();

    @DefaultMessage("Invite to this group chat")
    String inviteToThisGroupChat();

    @DefaultMessage("The group name has not valid characters")
    String notValidGroupChatName();

    @DefaultMessage("{0} Occupants")
    String occupants(int numberOfOccupants);

    @DefaultMessage("Group Chat Participants:")
    String occupantsLabelText();

    @DefaultMessage("Create a new group chat")
    String openNewGroupChat();

    @DefaultMessage("Create Group Chat")
    String openNewGroupChatAction();

    @DefaultMessage("Open new group chat")
    String openNewGroupChatTooltip();

    @DefaultMessage("The group chat must have at least one member")
    String selectionEmptyErrorMessage();

    @DefaultMessage("Group chat name can''t have white spaces")
    String spacesInGroupChatName();
}
