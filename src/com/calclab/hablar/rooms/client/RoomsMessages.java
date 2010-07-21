package com.calclab.hablar.rooms.client;

import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;
import com.google.gwt.i18n.client.Messages;

@DefaultLocale("en")
public interface RoomsMessages extends Messages {

    @DefaultMessage("Accept")
    String acceptAction();

    @DefaultMessage("Cancel")
    String cancelAction();

    @DefaultMessage("Close group chat ''{0}''")
    String confirmCloseRoomTitle(String roomName);

    @DefaultMessage("Are you sure you want to leave this group chat?")
    String confirmCloseRoom();

    @DefaultMessage("The group name can''t be empty")
    String emptyGroupChatName();

    @DefaultMessage("groupChat-{0}")
    String groupChatId(int groupChatNumber);

    @DefaultMessage("Group Chat Name:")
    String groupChatNameLabelText();

    @DefaultMessage("At group chat ''{0}'': {1}")
    String incommingAdminMessage(String groupChatName, String body);

    @DefaultMessage("At group chat ''{0}'', {1} says «{2}»")
    String incommingMessage(String groupChatName, String user, String msg);

    @DefaultMessage("Invitation message:")
    String invitationMessageLabelText();

    @DefaultMessage("You invited ''{0}'' to this conversation")
    String invitationSent(String name);

    @DefaultMessage("You invited ''{0}'' to this conversation with this message: {1}")
    String invitationSentWithReason(String name, String reason);

    @DefaultMessage("Invite more people to this group chat")
    String invitePeopleToGroupChat();

    @DefaultMessage("Invite to this group chat")
    String inviteToThisGroupChat();

    @DefaultMessage("The group name has not valid characters")
    String notValidGroupChatName();

    @DefaultMessage("''{0}'' has joined the conversation")
    String occupantHasJoined(String nick);

    @DefaultMessage("''{0}'' has left the conversation")
    String occupantHasLeft(String nick);

    @DefaultMessage("Occupant ''{0}'' has been modified.")
    String occupantModified(String nick);

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

    @DefaultMessage("''{0}'' has change the subject of this conversation to ''{1}''")
    String roomSubjectChanged(String nick, String newSubject);

    @DefaultMessage("The group chat must have at least one member")
    String selectionEmptyErrorMessage();

    @DefaultMessage("Group chat name can''t have white spaces")
    String spacesInGroupChatName();

    @DefaultMessage("This group chat is locked. We''re waiting for the server to unlock...")
    String waitingForUnlockRoom();
}
