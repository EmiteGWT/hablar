package com.calclab.hablar.basic.client.ui.pages;

import com.calclab.hablar.basic.client.ui.page.PageView;
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
    public void add(PageView pageView) {
	logic.add(pageView);
    }

    @Override
    public boolean close(PageView pageView) {
	return logic.close(pageView);
    }

    @Override
    public boolean hasPageView(PageView pageView) {
	return logic.hasPageView(pageView);
    }

    @Override
    public void hide(PageView pageView) {
	logic.hide(pageView);
    }

    @Override
    public void onPageClosed(Listener<PageView> listener) {
	logic.onPageClosed(listener);
    }

    @Override
    public void onPageOpened(Listener<PageView> listener) {
	logic.onPageOpened(listener);
    }

    @Override
    public void onStatusMessageChanged(Listener<PageView> listener) {
	logic.onStatusMessageChanged(listener);
    }

    @Override
    public void open(PageView pageView) {
	logic.open(pageView);
    }

}
