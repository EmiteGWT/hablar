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

    @DefaultMessage("See user profile")
    String seeUserProfileAction();

    @DefaultMessage("Close")
    String closeAction();

    @DefaultMessage("Profile of {0}")
    String profileOfBuddy(String name);

    @DefaultMessage("Name:")
    String nameLabelText();

    @DefaultMessage("Nickname:")
    String nicknameLabelText();

    @DefaultMessage("Family Name:")
    String familyNameLabelText();

    @DefaultMessage("Middle Name:")
    String middleNameLabelText();

    @DefaultMessage("Given Name:")
    String givenNameLabelText();

    @DefaultMessage("Organization:")
    String organizationLabelText();

    @DefaultMessage("Organization unit:")
    String organizationUnitLabelText();

    @DefaultMessage("Homepage:")
    String homepageLabelText();

    @DefaultMessage("Email:")
    String emailLabelText();
}
