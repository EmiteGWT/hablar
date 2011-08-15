package com.calclab.hablar.clipboard.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface ClipboardMessages extends Messages {
	public static final ClipboardMessages msg = GWT.create(ClipboardMessages.class);

	@DefaultMessage("Copy to clipboard")
	String copyToClipboardAction();

	@DefaultMessage("Copy conversation to clipboard")
	String copyConversationToClipboard();

	@DefaultMessage("Use your browser''s copy command to place this text into the clipboard")
	String useYourBrowser();

	@DefaultMessage("Close")
	String closeAction();
}
