package com.calclab.hablar.vcard.client;

import java.util.List;

import com.calclab.emite.core.client.packet.MatcherFactory;
import com.calclab.emite.core.client.packet.NoPacket;
import com.calclab.emite.xep.vcard.client.VCard;
import com.calclab.emite.xep.vcard.client.VCardEmail;
import com.calclab.emite.xep.vcard.client.VCardOrganization;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.vcard.client.VCardDisplay.Field;

public abstract class VCardPage extends PagePresenter<VCardDisplay> {
    public static final String TYPE = "VCard";

    public VCardPage(final HablarEventBus eventBus, final VCardDisplay display) {
	super(TYPE, eventBus, display);
    }

    protected void displayToModel(final VCard vcard) {
	vcard.setDisplayName(display.getField(Field.name).getText());
	vcard.setNickName(display.getField(Field.nickName).getText());

	vcard.setFamilyName(display.getField(Field.familyName).getText());
	vcard.setGivenName(display.getField(Field.givenName).getText());
	vcard.setMiddleName(display.getField(Field.middleName).getText());
	vcard.setURL(display.getField(Field.homepage).getText());

	final String newOrg = display.getField(Field.organizationName).getText();
	if (vcard.getFirstChild(MatcherFactory.byName(VCard.ORG)) == NoPacket.INSTANCE) {
	    new VCardOrganization(vcard.addChild(VCard.ORG));
	}
	vcard.getOrganization().setData(VCardOrganization.Data.ORGNAME, newOrg);

	vcard.getOrganization().setData(VCardOrganization.Data.ORGUNIT,
		display.getField(Field.organizationUnit).getText());

	final String newEmail = display.getField(Field.email).getText();
	final List<VCardEmail> emails = vcard.getEmails();
	for (final VCardEmail email : emails) {
	    if (emails.size() == 1 || email.isPreferred()) {
		email.setUserId(newEmail);
		break;
	    }
	}
	if (emails.size() == 0) {
	    final VCardEmail email = new VCardEmail(vcard.addChild(VCard.EMAIL));
	    email.setUserId(newEmail);
	}
    }

    protected void modelToDisplay(final VCard vcard) {
	if (vcard != null) {
	    display.getField(Field.name).setText(vcard.getDisplayName());
	    display.getField(Field.nickName).setText(vcard.getNickName());

	    display.getField(Field.familyName).setText(vcard.getFamilyName());
	    display.getField(Field.givenName).setText(vcard.getGivenName());
	    display.getField(Field.middleName).setText(vcard.getMiddleName());

	    display.getField(Field.homepage).setText(vcard.getURL());
	    display.getField(Field.organizationName).setText(
		    vcard.getOrganization().getData(VCardOrganization.Data.ORGNAME));
	    display.getField(Field.organizationUnit).setText(
		    vcard.getOrganization().getData(VCardOrganization.Data.ORGUNIT));

	    final List<VCardEmail> emails = vcard.getEmails();
	    for (final VCardEmail email : emails) {
		if (emails.size() == 1 || email.isPreferred()) {
		    display.getField(Field.email).setText(email.getUserId());
		    break;
		}
	    }
	} else {
	    clearVCardDisplay();
	}
    }

    private void clearVCardDisplay() {
	for (final Field field : Field.values()) {
	    display.getField(field).setText("");
	}
    }
}
