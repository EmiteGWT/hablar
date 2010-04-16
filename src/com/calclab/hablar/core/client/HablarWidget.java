package com.calclab.hablar.core.client;

import com.calclab.hablar.core.client.mvp.LoggerEventBus;
import com.calclab.hablar.core.client.pages.tabs.TabsLayout.TabHeaderSize;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class HablarWidget extends LayoutPanel implements HablarDisplay {
    private final Hablar hablar;

    @UiConstructor
    public HablarWidget(Layout layout, TabHeaderSize tabHeaderSize) {
	addStyleName("hablar-HablarWidget");
	if (layout == Layout.accordion) {
	    hablar = HablarPresenter.createAccordionPresenter(new LoggerEventBus(), this);
	} else if (layout == Layout.tabs) {
	    hablar = HablarPresenter.createTabsPresenter(new LoggerEventBus(), this, tabHeaderSize);
	} else {
	    throw new IllegalStateException("Unimplemented layout: " + layout);
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
