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

    protected void update(final VCard vcard) {
	vcard.setGivenName(display.getFirstName().getText());
	vcard.setMiddleName(display.getMiddleName().getText());
	vcard.setNickName(display.getNickName().getText());
	vcard.setFamilyName(display.getSurname().getText());
	vcard.getOrganization().setData(Data.ORGNAME, display.getOrganizationName().getText());
	final String email = display.getEmail().getText();
	if (email.length() > 0) {
	    // We only set one email (currently)
	    vcard.removeEmails();
	    final VCardEmail vEmail = new VCardEmail();
	    vEmail.set(email);
	    vEmail.setPref();
	    vcard.addEmail(vEmail);
	}
    }

    protected void update(final VCardResponse response) {
	if (response.hasVCard()) {
	    final VCard vcard = response.getVCard();
	    setVCard(vcard);
	}
    }

    private void setVCard(final VCard vcard) {
	display.getFirstName().setText(vcard.getGivenName());
	display.getMiddleName().setText(vcard.getMiddleName());
	display.getNickName().setText(vcard.getNickName());
	display.getSurname().setText(vcard.getFamilyName());
	display.getOrganizationName().setText(vcard.getOrganization().getData(Data.ORGNAME));
	final List<VCardEmail> emails = vcard.getEmails();
	for (final VCardEmail email : emails) {
	    if (emails.size() == 1 || email.isPref()) {
		display.getEmail().setText(email.get());
		break;
	    }
	}
    }
}
