package com.calclab.hablar.rooms.client;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class RoomNameTests {

    @Test
    public void shouldDecodeEncodedName() {
	final String original = "My room name";
	final String encoded = RoomName.encode(original, "uniqueValue");
	assertEquals(original, RoomName.decode(encoded));
    }

    @Test
    public void shouldNotDecodeNotEncodedName() {
	final String roomName = "My-room";
	assertEquals(roomName, RoomName.decode(roomName));
    }
}
