package com.calclab.hablar.icons.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.ImageResource;

public interface IconsBundle extends ClientBundle {
	public static final IconsBundle bundle = GWT.create(IconsBundle.class);

	@Source("buddy-add.png")
	ImageResource buddyAddIcon();

	@Source("buddy-small.png")
	ImageResource buddyIcon();

	@Source("buddy-small-dnd.png")
	ImageResource buddyIconDnd();

	@Source("buddy-small-off.png")
	ImageResource buddyIconOff();

	@Source("buddy-small-on.png")
	ImageResource buddyIconOn();

	@Source("buddy-small-wait.png")
	ImageResource buddyIconWait();

	@Source("chat.png")
	ImageResource chatIcon();

	@Source("chat-add.png")
	ImageResource chatAddIcon();

	@Source("clipboard.png")
	ImageResource clipboardIcon();

	@Source("close.png")
	ImageResource closeIcon();

	@Source("console.png")
	ImageResource consoleIcon();
	
	@Source("dnd.png")
	ImageResource dndIcon();

	@Source("group-add.png")
	ImageResource groupAddIcon();

	@Source("group-chat.png")
	ImageResource groupChatIcon();

	@Source("group-chat-add.png")
	ImageResource groupChatAddIcon();

	@Source("loading.gif")
	ImageResource loadingIcon();

	@Source("menu.png")
	ImageResource menuIcon();

	@Source("missing.png")
	ImageResource missingIcon();

	@Source("off.png")
	ImageResource offIcon();

	@Source("on.png")
	ImageResource onIcon();

	@Source("roster.png")
	ImageResource rosterIcon();

	@Source("search.png")
	ImageResource searchIcon();
	
	@Source("wait.png")
	ImageResource waitIcon();
}
