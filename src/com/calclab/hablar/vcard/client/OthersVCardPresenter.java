package com.calclab.hablar.vcard.client;

import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.hablar.core.client.mvp.HablarEventBus;

public class OthersVCardPresenter extends VCardPage {

    public OthersVCardPresenter(final HablarEventBus eventBus, final VCardDisplay display) {
	super(eventBus, display);
    }

    public void setUser(final XmppURI jid) {
	// get users vcard and display it
    }

}
