package com.calclab.hablar.rooms.client.room;

import com.calclab.emite.xep.muc.client.Room;
import com.calclab.hablar.chat.client.ui.ChatPage;
import com.calclab.hablar.core.client.ui.menu.Action;

public interface RoomPage extends ChatPage {
    void addAction(Action<RoomPage> action);

    String getChatName();

    Room getRoom();
}
