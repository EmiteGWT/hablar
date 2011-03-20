package com.calclab.hablar.signals.sound.client;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.events.UserMessageEvent;
import com.calclab.hablar.core.client.page.events.UserMessageHandler;

public class HablarSoundSignals {

    public HablarSoundSignals(final Hablar hablar, final SoundSignalsConfig soundConfig) {
	final SoundManager soundManager = new SoundManager(soundConfig);

	final HablarEventBus eventBus = hablar.getEventBus();
	eventBus.addHandler(UserMessageEvent.TYPE, new UserMessageHandler() {
	    public void onUserMessage(final UserMessageEvent event) {
		soundManager.play();
	    }
	});
    }

}
