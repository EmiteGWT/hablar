package com.calclab.hablar.vcard.client;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.xep.vcard.client.VCard;
import com.calclab.emite.xep.vcard.client.VCardManager;
import com.calclab.emite.xep.vcard.client.VCardResponse;
import com.calclab.emite.xep.vcard.client.VCard.Data;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.core.client.ui.icon.HablarIcons.IconType;
import com.calclab.hablar.user.client.EditorPage;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;

public class OwnVCardPresenter extends VCardPage implements EditorPage<VCardDisplay> {

    private boolean loading;
    private VCard currentVCard;

    public OwnVCardPresenter(final HablarEventBus eventBus, final VCardDisplay display) {
	super(eventBus, display);
	model.init(HablarIcons.get(IconType.buddyWait), "User profile");
	display.setAcceptVisible(false);
	display.setCancelVisible(false);
	display.setPageTitle("Your profile");
	requestVCard();
    }

    @Override
    public void saveData() {
	final Session session = Suco.get(Session.class);
	final VCardManager manager = Suco.get(VCardManager.class);
	if (!loading) {
	    final VCard newVCard = currentVCard != null ? currentVCard : new VCard();
	    updateVCard(newVCard);
	    final String jabberId = session.getCurrentUser().getJID().toString();
	    newVCard.setValue(Data.JABBERID, jabberId);
	    newVCard.setValue(Data.DESC, "Created with hablar");
	    currentVCard = newVCard;
	    manager.updateOwnVCard(newVCard, new Listener<VCardResponse>() {
		@Override
		public void onEvent(final VCardResponse response) {
		}
	    });
	}
    }

    @Override
    public void showData() {
    }

    private void requestVCard() {
	final VCardManager manager = Suco.get(VCardManager.class);
	setLoading(true);

	manager.requestOwnVCard(new Listener<VCardResponse>() {
	    @Override
	    public void onEvent(final VCardResponse response) {
		setCurrentVCard(response);
		setLoading(false);
	    }
	});
    }

    private void setLoading(final boolean loading) {
	this.loading = loading;
	display.setLoadingVisible(loading);
	display.setFormVisible(!loading);
    }

    protected void setCurrentVCard(final VCardResponse response) {
	currentVCard = response.getVCard();
	updateDisplay(currentVCard);
    }

}
