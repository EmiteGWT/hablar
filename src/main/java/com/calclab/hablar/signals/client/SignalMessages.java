package com.calclab.hablar.signals.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface SignalMessages extends Messages {
	public static final SignalMessages msg = GWT.create(SignalMessages.class);

	@DefaultMessage("Loading preferences...")
	String loadingPreferences();

	@DefaultMessage("Error saving preferences.")
	String saveError();

	@DefaultMessage("{0,number} conversations unread")
	@AlternateMessage({ "one", "1 conversation unread" })
	String unreadChats(@PluralCount int users);

	@DefaultMessage("Please log in to retrieve preferences")
	String waitingToSession();

	@DefaultMessage("Show unread conversations in title")
	String showUnreadConversations();

	@DefaultMessage("Show incoming message notifications")
	String showIncomingMessages();

	@DefaultMessage("Notification within the chat window")
	String jGrowlNotifierDisplayName();

	@DefaultMessage("Chat Notification")
	String browserPopupNotifierWindowTitle();

	@DefaultMessage("Browser Popup")
	String browserPopupNotifierDisplayName();
}
