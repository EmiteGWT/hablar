package com.calclab.hablar.client.ui.pages;

import com.calclab.hablar.client.ui.page.Page;
import com.calclab.hablar.client.ui.page.Page.Visibility;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.user.client.ui.Composite;

public abstract class AbstractPages extends Composite implements Pages, PagesPanel {
    private final PagesLogic logic;

    public AbstractPages() {
	logic = new PagesLogic(this);
    }

    @Override
    public void add(Page page, Visibility visibility) {
	logic.add(page, visibility);
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
