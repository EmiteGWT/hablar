package com.calclab.hablar.signals.sound.client;

import com.calclab.emite.browser.client.PageAssist;
import com.google.gwt.core.client.GWT;

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
    public String fileUrl = GWT.getModuleBaseURL() + "click.wav";
}
