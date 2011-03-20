package com.calclab.hablar.rooms.client;

public class RoomName {

    private static final String ID_PREFIX = ".hablar-";

    public static String decode(final String roomName) {
	final int prefixIndex = roomName.indexOf(ID_PREFIX);
	if (prefixIndex == -1) {
	    return roomName;
	} else {
	    return roomName.substring(0, prefixIndex).replace('_', ' ');
	}
    }

    public static String encode(final String name, final String id) {
	return name.replace(' ', '_') + ID_PREFIX + id;
    }

}
