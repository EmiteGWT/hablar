package com.calclab.hablar.vcard.client;

import com.calclab.emite.xep.vcard.client.VCard;
import com.calclab.emite.xep.vcard.client.VCardManager;
import com.calclab.emite.xep.vcard.client.VCardResponse;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.user.client.EditorPage;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;

public class OwnVCardPresenter extends VCardPage implements EditorPage<VCardDisplay> {

    public OwnVCardPresenter(final HablarEventBus eventBus, final VCardDisplay display) {
	super(eventBus, display);
	model.init(HablarIcons.get(IconType.buddyWait), "User profile");
	display.setAcceptVisible(false);
	display.setCancelVisible(false);
	display.setPageTitle("Your profile");
    }

    @Override
    public void saveData() {
	final VCardManager manager = Suco.get(VCardManager.class);
	manager.requestOwnVCard(new Listener<VCardResponse>() {
	    @Override
	    public void onEvent(final VCardResponse response) {
		final VCard vCard = response.hasVCard() ? response.getVCard() : new VCard();
		updateVCard(vCard);
		manager.updateOwnVCard(vCard, new Listener<VCardResponse>() {
		    @Override
		    public void onEvent(final VCardResponse response) {
			updateDisplay(response.getVCard());
		    }
		});

	    }
	});
    }

    @Override
    public void showData() {
	final VCardManager manager = Suco.get(VCardManager.class);
	manager.requestOwnVCard(new Listener<VCardResponse>() {
	    @Override
	    public void onEvent(final VCardResponse response) {
		updateDisplay(response.getVCard());
	    }
	});
    }

}
