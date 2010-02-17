package com.calclab.hablar.chat.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface ChatMessages extends Messages {
    @DefaultMessage("User {0} says «{1}»")
    String newChatFrom(String user, String msg);
}
