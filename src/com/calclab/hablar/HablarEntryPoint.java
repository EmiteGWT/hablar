package com.calclab.hablar;

import com.calclab.hablar.HablarConfig.Layout;
import com.calclab.hablar.basic.client.ui.HablarWidget;
import com.calclab.hablar.basic.client.ui.pages.PagesWidget;
import com.calclab.hablar.basic.client.ui.pages.panel.AccordionPages;
import com.calclab.hablar.basic.client.ui.pages.panel.TabPages;
import com.calclab.hablar.chat.client.HablarChat;
import com.calclab.hablar.login.client.HablarLogin;
import com.calclab.hablar.roster.client.HablarRoster;
import com.calclab.hablar.search.client.HablarSearch;
import com.calclab.hablar.signals.client.HablarSignals;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class HablarEntryPoint implements EntryPoint {

    @Override
    public void onModuleLoad() {
	final HablarConfig config = HablarConfig.getFromMeta();
	final HablarWidget hablar = createWidget(config, config);

	HablarChat.install(hablar);

	if (config.hasLogin) {
	    HablarLogin.install(hablar);
	}

	if (config.hasRoster) {
	    boolean isDocked = "left".equals(config.dockRoster);
	    HablarRoster.install(hablar, isDocked);
	}

	if (config.hasSearch) {
	    HablarSearch.install(hablar);
	}

	if (config.hasSignals) {
	    HablarSignals.install(hablar);
	}

	if (config.inline == null) {
	    createDialog(hablar, config);
	} else {
	    addHablarToDiv(hablar, config);
	}

    }

    private void addHablarToDiv(final HablarWidget hablar, final HablarConfig config) {
	setSize(hablar, config);
	RootPanel rootPanel = RootPanel.get(config.inline);
	if (rootPanel != null) {
	    rootPanel.add(hablar);
	} else {
	    throw new RuntimeException("The div with id " + config.inline + " is not found.");
	}
    }

    private DialogBox createDialog(final HablarWidget widget, HablarConfig config) {
	DialogBox dialog = new DialogBox();
	dialog.setText("Hablar");
	setSize(dialog, config);
	dialog.show();
	dialog.center();
	return dialog;
    }

    private HablarWidget createWidget(final HablarConfig config, HablarConfig config2) {
	PagesWidget pages = null;
	if (config.layout == Layout.accordion) {
	    pages = new PagesWidget(new AccordionPages());
	} else if (config.layout == Layout.tabs) {
	    pages = new PagesWidget(new TabPages());
	} else {
	    throw new RuntimeException("Layout not configured.");
	}
	HablarWidget hablar = new HablarWidget(pages);

	return hablar;
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
