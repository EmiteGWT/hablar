package com.calclab.hablar.signals.sound.client;

import com.allen_sauer.gwt.voices.client.Sound;
import com.allen_sauer.gwt.voices.client.SoundController;
import com.google.gwt.core.client.GWT;

public class SoundManager {
    private final Sound sound;

    public SoundManager(final SoundSignalsConfig config) {
	final SoundController soundController = new SoundController();
	soundController.setDefaultVolume(config.defaultVolume);

	final String fileUrl = config.fileUrl;

	final int lastDotIndex = fileUrl.lastIndexOf('.') + 1;
	final String extension = fileUrl.substring(lastDotIndex);

	String mimeTypeAudioMpeg = null;
	if (extension.equals("mp3")) {
	    mimeTypeAudioMpeg = Sound.MIME_TYPE_AUDIO_MPEG;
	} else if (extension.equals("wav")) {
	    mimeTypeAudioMpeg = Sound.MIME_TYPE_AUDIO_X_WAV;
	}
	sound = mimeTypeAudioMpeg != null ? soundController.createSound(mimeTypeAudioMpeg, fileUrl) : null;
    }

    public void play() {
	if (sound != null) {
	    GWT.log("SOUND! " + sound.getLoadState());
	    sound.play();
	}
    }

}
