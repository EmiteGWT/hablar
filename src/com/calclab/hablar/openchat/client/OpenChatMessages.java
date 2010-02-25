package com.calclab.hablar.openchat.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface OpenChatMessages extends Messages {

    @DefaultMessage("JabberId can''t be empty")
    String jabberIdIsEmpty();

    @DefaultMessage("It should be a valid Jabber ID like name@server.net")
    String jabberIdNotValid();
}
