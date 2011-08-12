package com.calclab.hablar.signals.client.sound;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;

public interface SoundBundle extends ClientBundle {
	public static final SoundBundle bundle = GWT.create(SoundBundle.class);

	@Source("click.wav")
	DataResource getClickSound();
}
