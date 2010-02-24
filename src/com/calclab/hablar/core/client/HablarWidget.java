package com.calclab.hablar.core.client;

import com.calclab.hablar.core.client.mvp.LoggerEventBus;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class HablarWidget extends LayoutPanel implements HablarDisplay {
    private final Hablar hablar;

    @UiConstructor
    public HablarWidget(final HablarDisplay.Layout layout) {
	addStyleName("hablar-HablarWidget");
	hablar = new HablarPresenter(new LoggerEventBus(), layout, this);
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
