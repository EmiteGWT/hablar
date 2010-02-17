package com.calclab.hablar.signals.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface SignalMessages extends Messages {
    @DefaultMessage("{0} conversation unread")
    @PluralText( { "one", "1 conversation unread" })
    String unreadChats(@PluralCount int users);
}
