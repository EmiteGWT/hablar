package com.calclab.hablar.vcard.client;

import com.calclab.hablar.core.client.mvp.Display;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.HasText;

public interface VCardDisplay extends Display {

    public static enum Field {
	name, nickName, familyName, givenName, middleName, organizationName, email, homepage
    }

    HasClickHandlers getCancel();

    HasText getField(Field field);

    HasText getLoading();

    void setAcceptVisible(boolean visible);

    void setCancelText(String text);

    void setCancelVisible(boolean visible);

    void setFormVisible(boolean visible);

    void setLoadingVisible(boolean visible);

    void setPageTitle(String title);

}
