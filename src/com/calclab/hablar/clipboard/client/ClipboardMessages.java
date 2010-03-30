package com.calclab.hablar.clipboard.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface ClipboardMessages extends Messages {

    @DefaultMessage("Copy to clipboard")
    String copyToClipboardAction();

    @DefaultMessage("Copy conversation to clipboard")
    String copyConversationToClipboard();

    @DefaultMessage("Use your browser''s copy command to place this text into the clipboard")
    String useYourBrowser();

    @DefaultMessage("Close")
    String closeAction();
}
