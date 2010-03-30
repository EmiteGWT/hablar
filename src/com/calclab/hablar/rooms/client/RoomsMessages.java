package com.calclab.hablar.rooms.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface RoomsMessages extends Messages {

    @DefaultMessage("The group name can''t be empty")
    String emptyGroupChatName();

    @DefaultMessage("Group Chat Name:")
    String groupChatNameLabelText();

    @DefaultMessage("Invitation message:")
    String invitationMessageLabelText();

    @DefaultMessage("Group Chat Participants:")
    String occupantsLabelText();

    @DefaultMessage("Cancel")
    String cancelAction();

    @DefaultMessage("groupChat-{0}")
    String groupChatId(int groupChatNumber);

    @DefaultMessage("At group chat ''{0}'': {1}")
    String incommingAdminMessage(String groupChatName, String body);

    @DefaultMessage("At group chat ''{0}'', {1} says «{2}»")
    String incommingMessage(String groupChatName, String user, String msg);

    @DefaultMessage("Invite more people to this group chat")
    String invitePeopleToGroupChat();

    @DefaultMessage("Invite to this group chat")
    String inviteToThisGroupChat();

    @DefaultMessage("The group name has not valid characters")
    String notValidGroupChatName();

    @DefaultMessage("{0} Occupants")
    String occupants(int numberOfOccupants);

    @DefaultMessage("Create a new group chat")
    String openNewGroupChat();

    @DefaultMessage("Create Group Chat")
    String openNewGroupChatAction();

    @DefaultMessage("Open new group chat")
    String openNewGroupChatTooltip();

    @DefaultMessage("Group chat name can''t have white spaces")
    String spacesInGroupChatName();

}
