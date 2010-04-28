package com.calclab.hablar.signals.sound.client;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.events.UserMessageEvent;
import com.calclab.hablar.core.client.page.events.UserMessageHandler;
import com.google.gwt.core.client.EntryPoint;

public class HablarSoundSignals implements EntryPoint {

    public static void install(final Hablar hablar) {
	final String prefix = "com.calclab.hablar.signals.sound.HablarSoundSignals/";
	final SoundManager soundManager = new SoundManager(prefix + "click.wav");

	final HablarEventBus eventBus = hablar.getEventBus();
	eventBus.addHandler(UserMessageEvent.TYPE, new UserMessageHandler() {
	    public void onUserMessage(final UserMessageEvent event) {
		soundManager.play();
	    }
	});
    }

    public static void install(final HablarWidget widget) {
	install(widget.getHablar());
    }

    @Override
    public void onModuleLoad() {
    }

}
