package com.calclab.hablar.vcard.client;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.xep.vcard.client.VCardManager;
import com.calclab.emite.xep.vcard.client.events.VCardResponseEvent;
import com.calclab.emite.xep.vcard.client.events.VCardResponseHandler;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

public class OthersVCardPresenter extends VCardPage {

    private final VCardManager vCardManager;

    public OthersVCardPresenter(final VCardManager vCardManager, final HablarEventBus eventBus,
	    final VCardDisplay display) {
	super(eventBus, display);
	this.vCardManager = vCardManager;
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

    @Override
    protected void onBeforeFocus() {
	display.setLoadingVisible(true);
	display.setFormVisible(false);
    }

    public void setUser(final XmppURI jid) {
	display.setPageTitle(I18nVCard.t.profileOfBuddy(jid.getShortName()));
	vCardManager.getUserVCard(jid, new VCardResponseHandler() {
	    @Override
	    public void onVCardResponse(final VCardResponseEvent event) {
		modelToDisplay(event.getVCardResponse().getVCard());
		display.setLoadingVisible(false);
		display.setFormVisible(true);
	    }
	});

    }
}
