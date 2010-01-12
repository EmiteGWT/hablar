package com.calclab.hablar.client.pages;

import com.calclab.suco.client.events.Event;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.user.client.ui.Composite;

public abstract class PagesWidget extends Composite {
    protected final Listener<String> statusListener;
    protected final Listener<PageWidget> openListener;
    private PageWidget currentPage;
    private final Event<String> onStatus;

    public PagesWidget() {
	this.onStatus = new Event<String>("pages.onStatus");

	statusListener = new Listener<String>() {
	    @Override
	    public void onEvent(String status) {
		onStatus.fire(status);
	    }
	};
	openListener = new Listener<PageWidget>() {
	    @Override
	    public void onEvent(PageWidget page) {
		if (currentPage != null && page.isOpened()) {
		    currentPage.setOpen(false);
		}
		currentPage = page;
	    }
	};
    }

    public void add(PageWidget page, boolean visible) {
	page.onStatusChanged(statusListener);
	page.onOpenChanged(openListener);
	addPage(page);
	if (visible) {
	    show(page);
	}
    }

    public PageWidget getCurrentPage() {
	return currentPage;
    }

    public abstract void remove(PageWidget page);

    public void show(PageWidget page) {
	page.setOpen(true);
	showPage(page);
    }

    protected abstract void addPage(PageWidget page);

    protected abstract void showPage(PageWidget page);
}
