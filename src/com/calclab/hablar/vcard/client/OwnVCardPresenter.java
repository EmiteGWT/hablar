package com.calclab.hablar.vcard.client;

import java.util.List;

import com.calclab.emite.core.client.xmpp.session.Session;
import com.calclab.emite.core.client.xmpp.session.Session.State;
import com.calclab.emite.xep.vcard.client.VCard;
import com.calclab.emite.xep.vcard.client.VCardEmail;
import com.calclab.emite.xep.vcard.client.VCardManager;
import com.calclab.emite.xep.vcard.client.VCardResponse;
import com.calclab.emite.xep.vcard.client.VCard.Data;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.ui.icon.HablarIcons;
import com.calclab.hablar.user.client.EditorPage;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;

public class OwnVCardPresenter extends VCardPage implements EditorPage<VCardDisplay> {

    private boolean loading;
    private VCard storedVCard;

    public OwnVCardPresenter(final HablarEventBus eventBus, final VCardDisplay display) {
	super(eventBus, display);
	String title = I18nVCard.t.ownVCardTitle();
	model.init(HablarIcons.getBundle().buddyIconWait(), title, title);
	display.setAcceptVisible(false);
	display.setCancelVisible(false);
	display.setPageTitle(I18nVCard.t.ownVCardTitle());
	setLoading(true, I18nVCard.t.waitingToLogin());

	final Session session = Suco.get(Session.class);
	session.onStateChanged(new Listener<Session>() {
	    @Override
	    public void onEvent(final Session session) {
		if (session.getState() == State.loggedIn) {
		    requestVCard();
		}
	    }
	});

	if (session.getState() == State.loggedIn) {
	    requestVCard();
	}
    }

    @Override
    public void saveData() {
	if (!loading) {
	    final VCard newVCard = new VCard();
	    displayToModel(newVCard);
	    if (hasChanges(storedVCard, newVCard)) {
		updateVCard(newVCard);
	    }
	}
    }

    @Override
    public void showData() {
    }

    private boolean hasChanges(final VCard original, final VCard changed) {
	if (original == null || !changed.getName().equals(original.getName())) {
	    return true;
	} else if (!changed.getNickName().equals(original.getNickName())) {
	    return true;
	} else if (!changed.getFamilyName().equals(original.getFamilyName())) {
	    return true;
	} else if (!changed.getMiddleName().equals(original.getMiddleName())) {
	    return true;
	} else if (!changed.getGivenName().equals(original.getGivenName())) {
	    return true;
	} else if (!changed.getOrganization().equals(original.getOrganization())) {
	    return true;
	} else if (!changed.getURL().equals(original.getURL())) {
	    return true;
	}
	final List<VCardEmail> emails = original.getEmails();
	if (emails.size() == 0) {
	    return true;
	}
	VCardEmail originalEmail = null;
	for (final VCardEmail email : emails) {
	    if (emails.size() == 1 || email.isPreferred()) {
		originalEmail = email;
		break;
	    }
	}
	// always one email since changed is a new VCard
	final VCardEmail newEmail = changed.getEmails().get(0);
	if (originalEmail == null || !newEmail.getUserId().equals(originalEmail.getUserId())) {
	    return true;
	}
	return false;
    }

    private void requestVCard() {
	final VCardManager manager = Suco.get(VCardManager.class);

	setLoading(true, I18nVCard.t.waitingForOwnVCard());
	manager.requestOwnVCard(new Listener<VCardResponse>() {
	    @Override
	    public void onEvent(final VCardResponse response) {
		storedVCard = response.getVCard();
		modelToDisplay(storedVCard);
		setLoading(false, null);
	    }
	});
    }

    private void setLoading(final boolean loading, final String message) {
	this.loading = loading;
	display.setLoadingVisible(loading);
	if (loading && message != null) {
	    display.getLoading().setText(message);
	}
	display.setFormVisible(!loading);
    }

    private void updateVCard(final VCard newVCard) {
	setLoading(true, I18nVCard.t.updatingOwnVCard());
	final Session session = Suco.get(Session.class);
	final VCardManager manager = Suco.get(VCardManager.class);
	final String jabberId = session.getCurrentUser().getJID().toString();
	newVCard.setValue(Data.JABBERID, jabberId);
	manager.updateOwnVCard(newVCard, new Listener<VCardResponse>() {
	    @Override
	    public void onEvent(final VCardResponse response) {
		if (response.isSuccess()) {
		    storedVCard = newVCard;
		}
		setLoading(false, null);
	    }
	});
    }

}
