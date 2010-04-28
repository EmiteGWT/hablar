package com.calclab.hablar.signals.sound.client;

import com.calclab.emite.browser.client.PageAssist;

public class SoundSignalsConfig {

    public static SoundSignalsConfig getFromMeta() {
	final SoundSignalsConfig config = new SoundSignalsConfig();

	final String url = PageAssist.getMeta("hablar.sound.audioFileURL");
	if (url != null) {
	    config.fileUrl = url;
	}

	return config;
    }

    public int defaultVolume = 50;
    public String fileUrl = "com.calclab.hablar.signals.sound.HablarSoundSignals/click.wav";
}
