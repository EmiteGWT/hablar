package com.calclab.hablar.signals.sound.client;

import com.allen_sauer.gwt.voices.client.Sound;
import com.allen_sauer.gwt.voices.client.SoundController;
import com.google.gwt.core.client.GWT;

public class SoundManager {
    private final Sound sound;

    public SoundManager(final String filePath) {
	final SoundController soundController = new SoundController();
	soundController.setDefaultVolume(50);

	final int lastDotIndex = filePath.lastIndexOf('.') + 1;
	final String extension = filePath.substring(lastDotIndex);

	String mimeTypeAudioMpeg = null;
	if (extension.equals("mp3")) {
	    mimeTypeAudioMpeg = Sound.MIME_TYPE_AUDIO_MPEG;
	} else if (extension.equals("wav")) {
	    mimeTypeAudioMpeg = Sound.MIME_TYPE_AUDIO_X_WAV;
	}
	sound = mimeTypeAudioMpeg != null ? soundController.createSound(mimeTypeAudioMpeg, filePath) : null;
    }

    public void play() {
	if (sound != null) {
	    GWT.log("SOUND! " + sound.getLoadState());
	    sound.play();
	}
    }

}
