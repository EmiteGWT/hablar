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

    public VCardPage(HablarEventBus eventBus, VCardDisplay display) {
	super(TYPE, "" + (++id), eventBus, display);
	model.init(HablarIcons.get(IconType.buddyWait), "User profile");
    }

    @Override
    public void saveData() {
	// should save vcard here
	// String nickname = display.getNickName().getText();
	// etc...
    }

    @Override
    public void showData() {
	// should load and display vcard here
	VCardManager manager = Suco.get(VCardManager.class);
	manager.requestOwnVCard(new Listener<VCardResponse>() {
	    @Override
	    public void onEvent(VCardResponse response) {
		if (response.hasVCard()) {
		    VCard vcard = response.getVCard();
		    display.getNickName().setText(vcard.getNickName());
		    // etc...
		}
	    }
	});
    }
}
