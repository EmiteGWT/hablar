package com.calclab.hablar.search.client;

import com.calclab.emite.browser.client.PageAssist;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.xep.search.client.SearchManager;
import com.calclab.suco.client.Suco;
import com.google.gwt.core.client.EntryPoint;

public class HablarSearchEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	final XmppURI host = XmppURI.uri(PageAssist.getMeta("emite.searchHost"));
	Suco.get(SearchManager.class).setHost(host);
    }

}
