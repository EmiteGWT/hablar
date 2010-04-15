package com.calclab.hablar.core.client;

import com.calclab.hablar.HablarConfig;
import com.calclab.hablar.core.client.mvp.LoggerEventBus;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class HablarWidget extends LayoutPanel implements HablarDisplay {
    private final Hablar hablar;

    @UiConstructor
    public HablarWidget(final HablarConfig config) {
	addStyleName("hablar-HablarWidget");
	if (config.layout == Layout.accordion) {
	    hablar = HablarPresenter.createAccordionPresenter(new LoggerEventBus(), this);
	} else if (config.layout == Layout.tabs) {
	    hablar = HablarPresenter.createTabsPresenter(new LoggerEventBus(), this, config.tabHeaderSize);
	} else {
	    throw new IllegalStateException("Unimplemented layout: " + config.layout);
	}
    }

    @Override
    public void forceLayout() {
	GWT.log("FORCE LAYOUT");
        super.forceLayout();
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    public Hablar getHablar() {
	return hablar;
    }

}
