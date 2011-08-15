package com.calclab.hablar.login.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface LoginMessages extends Messages {
	public static final LoginMessages msg = GWT.create(LoginMessages.class);

	@DefaultMessage("Connected as {0}")
	@Description("The specified user has connected")
	String connectedAs(@Example("john.doe") String userName);

	@DefaultMessage("Disconnected")
	String disconnected();

	@DefaultMessage("Sign in")
	String login();

	@DefaultMessage("Sign out")
	String logout();

	@DefaultMessage("Session state: {0}")
	String sessionState(String state);

	@DefaultMessage("Session: {0}")
	String sessionStateMessage(String state);

	@DefaultMessage("Wait...")
	String waitDots();

	@DefaultMessage("User:")
	String userLabelText();

	@DefaultMessage("Password:")
	String passwordLabelText();
}
