package com.calclab.hablar.rooms.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;
import com.google.gwt.i18n.client.LocalizableResource.GenerateKeys;

@DefaultLocale("en")
@GenerateKeys
public interface RoomsMessages extends Messages {

    @DefaultMessage("Invite more people to this group chat")
    String invitePeopleToRoom();

    @DefaultMessage("{0} Occupants")
    String occupants(int numberOfOccupants);

}
