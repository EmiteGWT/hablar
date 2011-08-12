package com.calclab.hablar.chat.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface ChatMessages extends Messages {
	public static final ChatMessages msg = GWT.create(ChatMessages.class);

	@DefaultMessage("{0} says «{1}»")
	String newChatFrom(String userName, String msg);

	@DefaultMessage("Send")
	String sendAction();

	@DefaultMessage("{0} is active")
	String stateActive(String userName);

	@DefaultMessage("{0} is available")
	String stateAvailable(String userName);

	@DefaultMessage("{0} is writing...")
	String stateComposing(String userName);

	@DefaultMessage("{0} has gone")
	String stateGone(String userName);

	@DefaultMessage("{0} is inactive")
	String stateInactive(String userName);

	@DefaultMessage("{0} is offline!")
	String stateOffline(String userName);

	@DefaultMessage("{0} is paused")
	String statePause(String userName);
}
