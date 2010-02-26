package com.calclab.hablar.vcard.client;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;

@DefaultLocale("en")
public interface VCardMessages extends Messages {

    @DefaultMessage("Your profile")
    String ownVCardTitle();

    @DefaultMessage("Saving your profile...")
    String updatingOwnVCard();

    @DefaultMessage("Loading your profile...")
    String waitingForOwnVCard();

    @DefaultMessage("Please log in to retrieve your profile")
    String waitingToLogin();

}
