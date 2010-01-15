package com.calclab.hablar.client;

import com.calclab.emite.browser.client.PageAssist;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.roster.SubscriptionHandler;
import com.calclab.emite.im.client.roster.SubscriptionHandler.Behaviour;
import com.calclab.emite.xep.search.client.SearchManager;
import com.calclab.hablar.client.ui.HablarResources;
import com.calclab.suco.client.Suco;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Hablar implements EntryPoint {

    @Override
    public void onModuleLoad() {
	SubscriptionHandler handler = Suco.get(SubscriptionHandler.class);
	handler.setBehaviour(Behaviour.acceptAll);

	HablarResources res = GWT.create(HablarResources.class);
	res.generalCSS().ensureInjected();

	XmppURI host = XmppURI.uri(PageAssist.getMeta("emite.searchHost"));
	Suco.get(SearchManager.class).setHost(host);

	HablarConfig config = HablarConfig.getFromMeta();

	if (config.inline == null) {
	    HablarDialog hablarDialog = new HablarDialog(config);
	    setSize(hablarDialog, config);
	    hablarDialog.show();
	    hablarDialog.center();
	} else {
	    HablarWidget widget = new HablarWidget(config);
	    setSize(widget, config);
	    RootPanel.get(config.inline).add(widget);
	}

    }

    private void setSize(Widget widget, HablarConfig config) {
	if (config.width != null) {
	    widget.setWidth(config.width);
	}
	if (config.height != null) {
	    widget.setHeight(config.height);
	}
    }

}
