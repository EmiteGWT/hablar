package com.calclab.hablar.client.ui.pages;

import com.calclab.hablar.client.ui.page.Page;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class PagesWidget extends Composite implements Pages {
    private final PagesLogic logic;

    public PagesWidget(PagesPanel panel) {
	logic = new PagesLogic(panel);
	initWidget((Widget) panel);
    }

    @Override
    public void add(Page page) {
	logic.add(page);
    }

    @Override
    public boolean hasPage(Page page) {
	return logic.hasPage(page);
    }

    @Override
    public void hide(Page page) {
	logic.hide(page);
    }

    @Override
    public void onStatusMessageChanged(Listener<Page> listener) {
	logic.onStatusMessageChanged(listener);
    }

    @Override
    public void open(Page page) {
	logic.open(page);
    }

    @Override
    public void show(Page page) {
	logic.show(page);
    }
}
