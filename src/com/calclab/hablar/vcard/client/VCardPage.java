package com.calclab.hablar.vcard.client;

import com.calclab.emite.xep.vcard.client.VCard;
import com.calclab.emite.xep.vcard.client.VCardManager;
import com.calclab.emite.xep.vcard.client.VCardResponse;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.page.PagePresenter;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.user.client.EditorPage;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;

public class VCardPage extends PagePresenter<VCardDisplay> implements EditorPage<VCardDisplay> {
    public static final String TYPE = "VCard";
    private static int id = 0;

    public VCardPage(final HablarEventBus eventBus, final VCardDisplay display) {
	super(TYPE, "" + (++id), eventBus, display);
	model.init(HablarIcons.get(IconType.buddyWait), "User profile");
    }

    @Override
    public void saveData() {
	final VCardManager manager = Suco.get(VCardManager.class);
	// It's an update, then first of all we get the current own vcard, and
	// we only update some fields
	manager.requestOwnVCard(new Listener<VCardResponse>() {
	    @Override
	    public void onEvent(final VCardResponse response) {
		if (response.hasVCard()) {
		    final VCard vcard = response.getVCard();
		    // should save vcard here
		    // String nickname = display.getNickName().getText();
		    // etc...
		    // String nickname = display.getNickName().getText();

		    // We need vcard.set methods here
		    manager.updateOwnVCard(vcard, new Listener<VCardResponse>() {
			@Override
			public void onEvent(final VCardResponse response) {
			    update(response);
			}
		    });
		}
	    }
	});
    }

    @Override
    public void showData() {
	// should load and display vcard here
	final VCardManager manager = Suco.get(VCardManager.class);
	manager.requestOwnVCard(new Listener<VCardResponse>() {
	    @Override
	    public void onEvent(final VCardResponse response) {
		update(response);
	    }
	});
    }

    private void update(final VCardResponse response) {
	if (response.hasVCard()) {
	    final VCard vcard = response.getVCard();
	    display.getEmail().setText(vcard.getJabberID());
	    display.getFirstName().setText(vcard.getGivenName());
	    display.getMiddleName().setText(vcard.getMiddleName());
	    display.getNickName().setText(vcard.getNickName());
	    display.getOrganizationName().setText(vcard.getOrganization().getName());
	    display.getSurname().setText(vcard.getFamilyName());
	}
    }
}
