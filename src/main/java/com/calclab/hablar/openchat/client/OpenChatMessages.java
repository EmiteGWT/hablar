package com.calclab.hablar.openchat.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;
import com.google.gwt.i18n.client.Messages;

@DefaultLocale("en")
public interface OpenChatMessages extends Messages {
	public static final OpenChatMessages msg = GWT.create(OpenChatMessages.class);

	@DefaultMessage("Add to roster")
	String addToRosterLabelText();

	@DefaultMessage("You can''t chat with yourself")
	String currentUserJidNotAllowed();

	@DefaultMessage("JabberId can''t be empty")
	String jabberIdIsEmpty();

	@DefaultMessage("Jabber ID:")
	String jabberIdLabelText();

	@DefaultMessage("It should be a valid Jabber ID like name@server.net")
	String jabberIdNotValid();

	@DefaultMessage("Open Chat")
	String openChatAction();

	@DefaultMessage("Open New Chat")
	String openNewChat();

	@DefaultMessage("Open a New Chat")
	String openNewChatLabelText();
}
