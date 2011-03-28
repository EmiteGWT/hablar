package com.calclab.hablar.vcard.client;

import java.util.List;

import com.calclab.emite.core.client.events.StateChangedEvent;
import com.calclab.emite.core.client.events.StateChangedHandler;
import com.calclab.emite.core.client.xmpp.session.SessionStates;
import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.emite.xep.vcard.client.VCard;
import com.calclab.emite.xep.vcard.client.VCardEmail;
import com.calclab.emite.xep.vcard.client.VCardManager;
import com.calclab.emite.xep.vcard.client.VCard.Data;
import com.calclab.emite.xep.vcard.client.events.VCardResponseEvent;
import com.calclab.emite.xep.vcard.client.events.VCardResponseHandler;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.hablar.core.client.ui.icon.Icons;
import com.calclab.hablar.user.client.EditorPage;
import com.google.gwt.core.client.GWT;

public class OwnVCardPresenter extends VCardPage implements EditorPage<VCardDisplay> {

    private boolean loading;
    private VCard storedVCard;
    private final VCardManager vCardManager;
    private final XmppSession session;

    public OwnVCardPresenter(final XmppSession session, final VCardManager vCardManager, final HablarEventBus eventBus,
	    final VCardDisplay display) {
	super(eventBus, display);
	this.session = session;
	this.vCardManager = vCardManager;
	final String title = I18nVCard.t.ownVCardTitle();
	model.init(Icons.BUDDY_WAIT, title, title);
	display.setAcceptVisible(false);
	display.setCancelVisible(false);
	display.setPageTitle(I18nVCard.t.ownVCardTitle());
	setLoading(true, I18nVCard.t.waitingToLogin());

	session.addSessionStateChangedHandler(true, new StateChangedHandler() {

	    @Override
	    public void onStateChanged(final StateChangedEvent event) {
		if (event.is(SessionStates.loggedIn)) {
		    requestVCard();
		}
	    }
	});
    }

    private boolean hasChanges(final VCard original, final VCard changed) {
	if ((original == null) || !changed.getName().equals(original.getName())) {
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
	    if ((emails.size() == 1) || email.isPreferred()) {
		originalEmail = email;
		break;
	    }
	}
	// always one email since changed is a new VCard
	final VCardEmail newEmail = changed.getEmails().get(0);
	if ((originalEmail == null) || !newEmail.getUserId().equals(originalEmail.getUserId())) {
	    return true;
	}
	return false;
    }

    private void requestVCard() {
	GWT.log("REQUEST VCARD");

	setLoading(true, I18nVCard.t.waitingForOwnVCard());

	vCardManager.requestOwnVCard(new VCardResponseHandler() {
	    @Override
	    public void onVCardResponse(final VCardResponseEvent event) {
		storedVCard = event.getVCardResponse().getVCard();
		modelToDisplay(storedVCard);
		setLoading(false, null);
	    }
	});
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

    private void setLoading(final boolean loading, final String message) {
	this.loading = loading;
	display.setLoadingVisible(loading);
	if (loading && (message != null)) {
	    display.getLoading().setText(message);
	}
	display.setFormVisible(!loading);
    }

    @Override
    public void showData() {
    }

    private void updateVCard(final VCard newVCard) {
	setLoading(true, I18nVCard.t.updatingOwnVCard());
	final String jabberId = session.getCurrentUserURI().getJID().toString();
	newVCard.setValue(Data.JABBERID, jabberId);

	vCardManager.updateOwnVCard(newVCard, new VCardResponseHandler() {
	    @Override
	    public void onVCardResponse(final VCardResponseEvent event) {
		if (event.getVCardResponse().isSuccess()) {
		    storedVCard = newVCard;
		}
		setLoading(false, null);
	    }
	});

    }

}
