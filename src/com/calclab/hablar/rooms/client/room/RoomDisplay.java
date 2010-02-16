package com.calclab.hablar.rooms.client.room;

import com.calclab.hablar.chat.client.ui.ChatDisplay;
import com.calclab.hablar.rooms.client.occupant.OccupantsDisplay;

public interface RoomDisplay extends ChatDisplay {
    OccupantsDisplay createOccupantsDisplay(String roomId);
}
