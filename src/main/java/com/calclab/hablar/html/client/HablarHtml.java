package com.calclab.hablar.html.client;

import com.calclab.emite.browser.client.PageAssist;
import com.calclab.emite.core.client.xmpp.session.XmppSession;
import com.calclab.hablar.client.EmiteCompleteGinjector;
import com.calclab.hablar.client.HablarComplete;
import com.calclab.hablar.client.HablarConfig;
import com.calclab.hablar.console.client.HablarConsole;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.icons.alt.client.AltIcons;
import com.calclab.hablar.icons.def.client.DefaultIcons;
import com.calclab.hablar.icons.ie6gif.client.IE6GifIcons;
import com.calclab.hablar.login.client.HablarLogin;
import com.calclab.hablar.login.client.LoginConfig;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Use Hablar without using GWT.
 */
public class HablarHtml implements EntryPoint {
    private void addHablarToDiv(final HablarWidget hablar, final HtmlConfig htmlConfig) {
	setSize(hablar, htmlConfig);
	final RootPanel rootPanel = RootPanel.get(htmlConfig.inline);
	if (rootPanel != null) {
	    rootPanel.add(hablar);
	} else {
	    throw new RuntimeException("The div with id " + htmlConfig.inline + " is not found.");
	}
    }

    private DialogBox createDialog(final HablarWidget widget, final HtmlConfig htmlConfig) {
	final DialogBox dialog = new DialogBox();
	dialog.setText("Hablar");
	setSize(dialog, htmlConfig);
	dialog.show();
	dialog.center();
	return dialog;
    }

    @Override
    public void onModuleLoad() {
	final GWT.UncaughtExceptionHandler uncaughtExceptionHandler = new GWT.UncaughtExceptionHandler() {
	    @Override
	    public void onUncaughtException(final Throwable e) {
		GWT.log("UncaughtException: ", e);
	    }
	};
	// handle the unexpected after onModuleLoad()
	GWT.setUncaughtExceptionHandler(uncaughtExceptionHandler);

	try {
	    onModuleLoadCont();
	} catch (final RuntimeException ex) {
	    // use our handler rather than duplicate code
	    uncaughtExceptionHandler.onUncaughtException(ex);
	}
    }

    private void onModuleLoadCont() {
	final String icons = PageAssist.getMeta("hablar.icons");
	if ("alt".equals(icons)) {
	    AltIcons.load();
	} else if ("ie6".equals(icons)) {
	    IE6GifIcons.load();
	} else {
	    DefaultIcons.load();
	}

	final HablarConfig config = HablarConfig.getFromMeta();
	final HtmlConfig htmlConfig = HtmlConfig.getFromMeta();
	htmlConfig.hasLogger = true;
	final HablarWidget widget = new HablarWidget(config.layout, config.tabHeaderSize);
	final Hablar hablar = widget.getHablar();

	final EmiteCompleteGinjector ginjector = GWT.create(EmiteCompleteGinjector.class);
	final XmppSession session = ginjector.getXmppSession();

	HablarComplete.install(hablar, config, ginjector);

	if (htmlConfig.hasLogger) {
	    new HablarConsole(hablar, ginjector.getXmppConnection(), session);
	}

	if (htmlConfig.hasLogin) {
	    new HablarLogin(hablar, LoginConfig.getFromMeta(), session);
	}

	if (htmlConfig.inline == null) {
	    createDialog(widget, htmlConfig);
	} else {
	    addHablarToDiv(widget, htmlConfig);
	}
    }

    private void setSize(final Widget widget, final HtmlConfig htmlConfig) {
	if (htmlConfig.width != null) {
	    widget.setWidth(htmlConfig.width);
	}
	if (htmlConfig.height != null) {
	    widget.setHeight(htmlConfig.height);
	}
    }

}
