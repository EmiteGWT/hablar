package com.calclab.hablar.vcard.client;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.xep.vcard.client.VCardManager;
import com.calclab.emite.xep.vcard.client.VCardResponse;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class OthersVCardPresenter extends VCardPage {

    public OthersVCardPresenter(final HablarEventBus eventBus, final VCardDisplay display) {
	super(eventBus, display);
	display.setAcceptVisible(false);
	display.setCancelVisible(true);
	display.setCancelText(I18nVCard.t.closeAction());
	display.getCancel().addClickHandler(new ClickHandler() {
	    @Override
	    public void onClick(final ClickEvent event) {
		requestVisibility(Visibility.notFocused);
	    }
	});
    }

    public void setUser(final XmppURI jid) {
	display.setPageTitle(I18nVCard.t.profileOfBuddy(jid.getShortName()));
	final VCardManager manager = Suco.get(VCardManager.class);
	manager.getUserVCard(jid, new Listener<VCardResponse>() {
	    @Override
	    public void onEvent(final VCardResponse response) {
		modelToDisplay(response.getVCard());
		display.setLoadingVisible(false);
		display.setFormVisible(true);
	    }
	});
    }

    @Override
    protected void onBeforeFocus() {
	display.setLoadingVisible(true);
	display.setFormVisible(false);
    }
}
