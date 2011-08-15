package com.calclab.hablar.signals.client.sound;

import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.events.UserMessageEvent;
import com.calclab.hablar.core.client.page.events.UserMessageHandler;

public class HablarSoundSignals {

	public HablarSoundSignals(final Hablar hablar) {
		final HablarEventBus eventBus = hablar.getEventBus();
		eventBus.addHandler(UserMessageEvent.TYPE, new UserMessageHandler() {
			@Override
			public void onUserMessage(final UserMessageEvent event) {
				SoundManager.play(SoundBundle.bundle.getClickSound().getUrl());
			}
		});
	}

}
