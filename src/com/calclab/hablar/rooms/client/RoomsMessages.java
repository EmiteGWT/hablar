package com.calclab.hablar.rooms.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;
import com.google.gwt.i18n.client.LocalizableResource.GenerateKeys;

@DefaultLocale("en")
@GenerateKeys
public interface RoomsMessages extends Messages {

    @DefaultMessage("The group name can''t be empty")
    String emptyGroupChatName();

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

    @DefaultMessage("Group chat name can''t have white spaces")
    String spacesInGroupChatName();

}
