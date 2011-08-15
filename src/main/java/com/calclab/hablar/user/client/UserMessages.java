package com.calclab.hablar.user.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface UserMessages extends Messages {
	public static final UserMessages msg = GWT.create(UserMessages.class);

	@DefaultMessage("Available")
	String available();

	@DefaultMessage("Available with Custom Message...")
	String availableCustom();

	@DefaultMessage("Busy")
	String busy();

	@DefaultMessage("Busy with Custom Message...")
	String busyCustom();

	@DefaultMessage("Clear custom messages")
	String clearCustom();

	@DefaultMessage("Disconnected")
	String notLoggedIn();

	@DefaultMessage("Your status")
	String presencePageTitle();

	@DefaultMessage("Status:")
	String statusLabelText();

	@DefaultMessage("Close")
	String closeAction();
}
