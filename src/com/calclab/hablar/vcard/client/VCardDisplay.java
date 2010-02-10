package com.calclab.hablar.vcard.client;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.user.client.ui.HasText;

public interface VCardDisplay extends Display {

    HasText getEmail();

    HasText getFirstName();

    HasText getMiddleName();

    HasText getNickName();

    HasText getOrganizationName();

    HasText getSurname();

}
