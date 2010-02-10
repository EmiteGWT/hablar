package com.calclab.hablar.core.client;

import com.calclab.hablar.core.client.mvp.LoggerEventBus;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.Widget;

public class HablarWidget extends Composite implements HablarDisplay {
    private LayoutPanel panel;
    private final Hablar hablar;

    public HablarWidget(HablarDisplay.Layout layout) {
	initWidget(panel = new LayoutPanel());
	addStyleName("hablar-HablarWidget");
	hablar = new HablarPresenter(new LoggerEventBus(), layout, this);
    }

    @Override
    public Widget asWidget() {
	return this;
    }

    @Override
    public LayoutPanel getContainer() {
	return panel;
    }

    public Hablar getHablar() {
	return hablar;
    }

    public LayoutPanel getPanel() {
	return panel;
    }

}
