package com.calclab.hablar.rooms.client.room;

import com.calclab.emite.xep.muc.client.Room;
import com.calclab.hablar.chat.client.MessageDataProvider;
import com.calclab.hablar.core.client.page.Page;
import com.calclab.hablar.core.client.ui.menu.Action;

public interface RoomPage extends Page<RoomDisplay>, MessageDataProvider {
    void addAction(Action<RoomPage> action);

    Room getRoom();

    String getChatName();
}
