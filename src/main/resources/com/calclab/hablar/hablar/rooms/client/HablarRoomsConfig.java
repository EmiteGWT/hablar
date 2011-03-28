package com.calclab.hablar.rooms.client;

import com.calclab.emite.browser.client.PageAssist;

public class HablarRoomsConfig {

    public static HablarRoomsConfig getFromMeta() {
	final HablarRoomsConfig config = new HablarRoomsConfig();
	config.roomsService = PageAssist.getMeta("hablar.roomService");
	config.sendButtonVisible = PageAssist.isMetaTrue("hablar.sendButton");
	return config;
    }

    /**
     * Show or not the send button in ui
     */
    public boolean sendButtonVisible;

    /**
     * The room service name
     */
    public String roomsService;

}
