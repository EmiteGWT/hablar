package com.calclab.hablar.login.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface LoginMessages extends Messages {
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

    @DefaultMessage("Wait...")
    String waitDots();
}
