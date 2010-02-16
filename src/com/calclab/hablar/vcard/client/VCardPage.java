package com.calclab.hablar.vcard.client;

import java.util.List;

import com.calclab.emite.xep.vcard.client.VCard;
import com.calclab.emite.xep.vcard.client.VCardEmail;
import com.calclab.emite.xep.vcard.client.VCardResponse;
import com.calclab.emite.xep.vcard.client.VCardOrganization.Data;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;

public abstract class VCardPage extends PagePresenter<VCardDisplay> {
    public static final String TYPE = "VCard";

    public VCardPage(final HablarEventBus eventBus, final VCardDisplay display) {
	super(TYPE, eventBus, display);
    }

    private void setVCard(final VCard vcard) {
	display.getFirstName().setText(vcard.getGivenName());
	display.getMiddleName().setText(vcard.getMiddleName());
	display.getNickName().setText(vcard.getNickName());
	display.getSurname().setText(vcard.getFamilyName());
	display.getOrganizationName().setText(vcard.getOrganization().getData(Data.ORGNAME));
	final List<VCardEmail> emails = vcard.getEmails();
	for (final VCardEmail email : emails) {
	    if (emails.size() == 1 || email.isPreferred()) {
		display.getEmail().setText(email.getUserId());
		break;
	    }
	}
    }

    protected void update(final VCardResponse response) {
	if (response.hasVCard()) {
	    final VCard vcard = response.getVCard();
	    setVCard(vcard);
	}
    }
}
