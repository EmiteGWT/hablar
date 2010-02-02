package com.calclab.hablar.signals.client;

import com.calclab.hablar.basic.client.ui.HablarWidget;
import com.google.gwt.core.client.EntryPoint;

public class HablarSignals implements EntryPoint {

    public static void install(final HablarWidget hablar) {
	final ChatSignalsLogic chatSignalsLogic = new ChatSignalsLogic(hablar.getEventBus());
	new WindowTitleManager(chatSignalsLogic);

    }

    @Override
    public void onModuleLoad() {
    }

}
