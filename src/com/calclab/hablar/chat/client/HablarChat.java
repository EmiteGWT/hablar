package com.calclab.hablar.chat.client;

import com.calclab.hablar.basic.client.ui.HablarWidget;
import com.google.gwt.core.client.EntryPoint;

public class HablarChat implements EntryPoint {

    public static void install(HablarWidget hablar) {
	new ChatManagerLogic(ChatConfig.getFromMeta(), hablar.getPages());
    }

    @Override
    public void onModuleLoad() {

    }

}
