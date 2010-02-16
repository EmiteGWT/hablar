package com.calclab.hablar.vcard.client;

import java.util.List;

import com.calclab.emite.xep.vcard.client.VCard;
import com.calclab.emite.xep.vcard.client.VCardEmail;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.vcard.client.VCardDisplay.Field;

public abstract class VCardPage extends PagePresenter<VCardDisplay> {
    public static final String TYPE = "VCard";

    public VCardPage(final HablarEventBus eventBus, final VCardDisplay display) {
	super(TYPE, eventBus, display);
    }

    protected void updateDisplay(final VCard vcard) {
	if (vcard != null) {
	    display.getField(Field.name).setText(vcard.getDisplayName());
	    display.getField(Field.nickName).setText(vcard.getNickName());

	    display.getField(Field.familyName).setText(vcard.getFamilyName());
	    display.getField(Field.givenName).setText(vcard.getGivenName());
	    display.getField(Field.middleName).setText(vcard.getMiddleName());

	    display.getField(Field.homepage).setText(vcard.getURL());

	    final List<VCardEmail> emails = vcard.getEmails();
	    for (final VCardEmail email : emails) {
		if (emails.size() == 1 || email.isPreferred()) {
		    display.getField(Field.email).setText(email.getUserId());
		    break;
		}
	    }
	}
    }

    protected void updateVCard(final VCard vcard) {
	vcard.setDisplayName(display.getField(Field.name).getText());
	vcard.setNickName(display.getField(Field.nickName).getText());

	vcard.setFamilyName(display.getField(Field.familyName).getText());
	vcard.setGivenName(display.getField(Field.givenName).getText());
	vcard.setMiddleName(display.getField(Field.middleName).getText());

	vcard.setURL(display.getField(Field.homepage).getText());

	final String email = display.getField(Field.email).getText();
	if (email.length() > 0) {
	    vcard.addEmail(new VCardEmail(email, true));
	}
    }
}
