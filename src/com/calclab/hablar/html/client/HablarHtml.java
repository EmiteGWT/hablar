package com.calclab.hablar.html.client;

import com.calclab.hablar.HablarComplete;
import com.calclab.hablar.HablarConfig;
import com.calclab.hablar.console.client.HablarConsole;
import com.calclab.hablar.core.client.Hablar;
import com.calclab.hablar.core.client.HablarWidget;
import com.calclab.hablar.login.client.HablarLogin;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Use Hablar without using GWT.
 */
public class HablarHtml implements EntryPoint {
    @Override
    public void onModuleLoad() {
	final GWT.UncaughtExceptionHandler uncaughtExceptionHandler = new GWT.UncaughtExceptionHandler() {
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

    private void onModuleLoadCont() {
	// DefaultIcons.load();
	final HablarConfig config = HablarConfig.getFromMeta();
	final HtmlConfig htmlConfig = HtmlConfig.getFromMeta();
	htmlConfig.hasLogger = true;
	final HablarWidget widget = new HablarWidget(config.layout, config.tabHeaderSize);
	final Hablar hablar = widget.getHablar();

	HablarComplete.install(hablar, config);

	if (htmlConfig.hasLogger) {
	    HablarConsole.install(hablar);
	}

	if (htmlConfig.hasLogin) {
	    HablarLogin.install(hablar);
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
