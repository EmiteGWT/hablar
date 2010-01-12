package com.calclab.hablar.client.ui.pages;

import com.calclab.suco.client.events.Event;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.user.client.ui.Composite;

public abstract class PagesWidget extends Composite {
    public static enum Position {
	normal, visible, WEST
    }
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

    public void add(PageWidget page, Position position) {
	page.onStatusChanged(statusListener);
	page.onOpenChanged(openListener);
	addPage(page);
	if (position == Position.visible) {
	    show(page);
	} else if (position == Position.normal && currentPage != null) {
	    show(currentPage);
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
