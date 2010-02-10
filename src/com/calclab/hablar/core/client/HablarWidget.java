package com.calclab.hablar.core.client;

import com.calclab.hablar.core.client.mvp.LoggerEventBus;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class HablarWidget extends LayoutPanel implements HablarDisplay {
    private final Hablar hablar;

    public HablarWidget(HablarDisplay.Layout layout) {
	addStyleName("hablar-HablarWidget");
	hablar = new HablarPresenter(new LoggerEventBus(), layout, this);
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    public Hablar getHablar() {
	return hablar;
    }

}
