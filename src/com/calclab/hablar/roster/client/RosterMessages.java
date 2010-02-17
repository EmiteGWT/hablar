package com.calclab.hablar.roster.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface RosterMessages extends Messages {
    @DefaultMessage("Contacts")
    @Description("The roster panel title")
    String contacts();

}
