package com.calclab.hablar.openchat.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface OpenChatMessages extends Messages {

    @DefaultMessage("Add to roster")
    String addToRosterLabelText();

    @DefaultMessage("Cancel")
    String cancelAction();

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
