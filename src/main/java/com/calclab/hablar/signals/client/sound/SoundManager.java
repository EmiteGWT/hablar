package com.calclab.hablar.signals.client.sound;

import com.google.gwt.media.client.Audio;

public class SoundManager {

	private final static Audio audio = Audio.createIfSupported();

	private SoundManager() {
	}

	public static void play(String url) {
		if (audio != null) {
			audio.pause();
			audio.setSrc(url);
			audio.play();
		}
	}

}
