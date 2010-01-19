package com.calclab.hablar.basic.client;

import com.calclab.emite.browser.client.PageAssist;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.im.client.roster.SubscriptionHandler;
import com.calclab.emite.im.client.roster.SubscriptionHandler.Behaviour;
import com.calclab.emite.xep.search.client.SearchManager;
import com.calclab.hablar.basic.client.ui.HablarResources;
import com.calclab.hablar.basic.client.ui.styles.DefaultHablarStyles;
import com.calclab.suco.client.Suco;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class HablarBasicEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	DefaultHablarStyles.init();

	final SubscriptionHandler handler = Suco.get(SubscriptionHandler.class);
	handler.setBehaviour(Behaviour.acceptAll);

	final HablarResources res = GWT.create(HablarResources.class);
	res.generalCSS().ensureInjected();

	// FIXME: revise this (needed to Mock i18n for tests)
	Suco.install(new HablarModule());

	final XmppURI host = XmppURI.uri(PageAssist.getMeta("emite.searchHost"));
	Suco.get(SearchManager.class).setHost(host);

	final HablarConfig config = HablarConfig.getFromMeta();

	if (config.inline == null) {
	    final HablarDialog hablarDialog = new HablarDialog(config);
	    setSize(hablarDialog, config);
	    hablarDialog.show();
	    hablarDialog.center();
	} else {
	    final HablarWidget widget = new HablarWidget(config);
	    setSize(widget, config);
	    RootPanel rootPanel = RootPanel.get(config.inline);
	    if (rootPanel != null) {
		rootPanel.add(widget);
	    } else {
		throw new RuntimeException("The div with id " + config.inline + " is not found.");
	    }
	}

    }

    private void setSize(final Widget widget, final HablarConfig config) {
	if (config.width != null) {
	    widget.setWidth(config.width);
	}
	if (config.height != null) {
	    widget.setHeight(config.height);
	}
    }

}
