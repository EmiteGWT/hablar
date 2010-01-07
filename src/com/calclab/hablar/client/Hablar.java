package com.calclab.hablar.client;

import com.calclab.emite.browser.client.PageAssist;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.xep.search.client.SearchManager;
import com.calclab.suco.client.Suco;
import com.google.gwt.core.client.EntryPoint;

public class Hablar implements EntryPoint {

    @Override
    public void onModuleLoad() {
	XmppURI host = XmppURI.uri(PageAssist.getMeta("emite.searchHost"));
	Suco.get(SearchManager.class).setHost(host);

	HablarConfig config = new HablarConfig();
	config.hasLogin = !"false".equals(PageAssist.getMeta("hablar.loginWidget"));
	config.hasRoster = !"false".equals(PageAssist.getMeta("hablar.rosterWidget"));
	config.hasSearch = !"false".equals(PageAssist.getMeta("hablar.searchWidget"));

	HablarDialog hablarDialog = new HablarDialog(config);
	hablarDialog.show();
	hablarDialog.center();
    }

}
