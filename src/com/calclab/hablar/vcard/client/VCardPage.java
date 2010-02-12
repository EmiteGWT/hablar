package com.calclab.hablar.vcard.client;

import com.calclab.emite.xep.vcard.client.VCard;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;

public abstract class VCardPage extends PagePresenter<VCardDisplay> {
    public static final String TYPE = "VCard";

    public VCardPage(final HablarEventBus eventBus, final VCardDisplay display) {
	super(TYPE, eventBus, display);
    }

    protected void setVCard(final VCard vcard) {
	display.getEmail().setText(vcard.getJabberID());
	display.getFirstName().setText(vcard.getGivenName());
	display.getMiddleName().setText(vcard.getMiddleName());
	display.getNickName().setText(vcard.getNickName());
	display.getOrganizationName().setText(vcard.getOrganization().getName());
	display.getSurname().setText(vcard.getFamilyName());
    }

}
