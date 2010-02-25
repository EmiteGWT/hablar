package com.calclab.hablar.rooms.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;
import com.google.gwt.i18n.client.LocalizableResource.GenerateKeys;

@DefaultLocale("en")
@GenerateKeys
public interface RoomsMessages extends Messages {

    @DefaultMessage("The group name can''t be empty")
    String emptyRoomName();

    @DefaultMessage("At room ''{0}'': {1}")
    String incommingAdminMessage(String roomName, String body);

    @DefaultMessage("At room ''{0}'', {1} says «{2}»")
    String incommingMessage(String roomName, String user, String msg);

    @DefaultMessage("Invite more people to this group chat")
    String invitePeopleToRoom();

    @DefaultMessage("The group name has not valid characters")
    String notValidRoomName();

    @DefaultMessage("{0} Occupants")
    String occupants(int numberOfOccupants);

    @DefaultMessage("Create a new group chat")
    String openNewRoom();

    @DefaultMessage("Create Group Chat")
    String openNewRoomAction();

    @DefaultMessage("groupChat-{0}")
    String roomId(int roomNumber);

    @DefaultMessage("Group chat name can''t have white spaces")
    String spacesInRoomName();
}
