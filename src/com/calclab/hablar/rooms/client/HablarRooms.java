package com.calclab.hablar.rooms.client;

import com.calclab.hablar.core.client.Hablar;
import com.google.gwt.core.client.EntryPoint;

public class HablarRooms implements EntryPoint {

    public static void install(final Hablar hablar) {
	install(hablar, HablarRoomsConfig.getFromMeta());
    }

    public static void install(final Hablar hablar, final HablarRoomsConfig config) {
	new HablarRoomManager(hablar, config);
    }

    @Override
    public void onModuleLoad() {
    }

}
