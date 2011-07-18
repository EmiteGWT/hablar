package com.calclab.hablar.icons.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;

public interface AltIconsBundle extends IconsBundle {
	public static final AltIconsBundle bundle = GWT.create(AltIconsBundle.class);

	@Source("alt/buddy-small.png")
	ImageResource buddyIcon();

	@Source("alt/buddy-small-dnd.png")
	ImageResource buddyIconDnd();

	@Source("alt/buddy-small-off.png")
	ImageResource buddyIconOff();

	@Source("alt/buddy-small-on.png")
	ImageResource buddyIconOn();

	@Source("alt/buddy-small-wait.png")
	ImageResource buddyIconWait();

	@Source("alt/chat.png")
	ImageResource chatIcon();

	@Source("alt/dnd.png")
	ImageResource dndIcon();

	@Source("alt/menu.png")
	ImageResource menuIcon();

	@Source("alt/off.png")
	ImageResource offIcon();

	@Source("alt/on.png")
	ImageResource onIcon();

	@Source("alt/roster.png")
	ImageResource rosterIcon();
}
