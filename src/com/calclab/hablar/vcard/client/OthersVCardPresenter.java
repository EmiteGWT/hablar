package com.calclab.hablar.vcard.client;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.xep.vcard.client.VCardManager;
import com.calclab.emite.xep.vcard.client.VCardResponse;
import com.calclab.hablar.core.client.mvp.HablarEventBus;
import com.calclab.suco.client.Suco;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;

public class OthersVCardPresenter extends VCardPage {

    public OthersVCardPresenter(final HablarEventBus eventBus, final VCardDisplay display) {
	super(eventBus, display);
    }

    public void setUser(final XmppURI jid) {
	GWT.log(jid.toString(), null);
	final VCardManager manager = Suco.get(VCardManager.class);
	manager.getUserVCard(jid, new Listener<VCardResponse>() {
	    @Override
	    public void onEvent(final VCardResponse response) {
		update(response);
	    }
	});
    }
}
