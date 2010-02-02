package com.calclab.hablar.chat.client;

import com.calclab.hablar.basic.client.ui.HablarWidget;
import com.calclab.hablar.chat.client.ui.ChatManagerLogic;
import com.google.gwt.core.client.EntryPoint;

public class HablarChat implements EntryPoint {

    public static void install(HablarWidget hablar) {
	install(hablar, ChatConfig.getFromMeta());
    }

    private static void install(HablarWidget hablar, ChatConfig config) {
	new ChatManagerLogic(hablar.getEventBus(), config, hablar.getPages());
    }

    @Override
    public void onModuleLoad() {

    }

}
