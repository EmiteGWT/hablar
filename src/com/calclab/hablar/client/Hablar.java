package com.calclab.hablar.client;

import com.calclab.emite.browser.client.PageAssist;
import com.calclab.emite.core.client.xmpp.stanzas.XmppURI;
import com.calclab.emite.xep.search.client.SearchManager;
import com.calclab.suco.client.Suco;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Hablar implements EntryPoint {

    @Override
    public void onModuleLoad() {
	XmppURI host = XmppURI.uri(PageAssist.getMeta("emite.searchHost"));
	Suco.get(SearchManager.class).setHost(host);

	HablarConfig config = getConfiguration();

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

    private HablarConfig getConfiguration() {
	HablarConfig config = new HablarConfig();
	config.hasLogin = !"false".equals(PageAssist.getMeta("hablar.loginWidget"));
	config.hasRoster = !"false".equals(PageAssist.getMeta("hablar.rosterWidget"));
	config.hasSearch = !"false".equals(PageAssist.getMeta("hablar.searchWidget"));

	String layout = PageAssist.getMeta("hablar.layout");
	if ("accordion".equals(layout))
	    config.layout = HablarConfig.Layout.accordion;
	else if ("dock".equals(layout))
	    config.layout = HablarConfig.Layout.dock;
	else
	    config.layout = HablarConfig.Layout.tabs;

	config.inline = PageAssist.getMeta("hablar.inline");
	config.width = PageAssist.getMeta("hablar.width");
	config.height = PageAssist.getMeta("hablar.height");
	if (config.width == null)
	    config.width = "400px";
	if (config.height == null)
	    config.height = "400px";
	return config;
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
