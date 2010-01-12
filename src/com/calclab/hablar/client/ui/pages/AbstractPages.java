package com.calclab.hablar.client.ui.pages;

import com.calclab.suco.client.events.Event;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;

public abstract class AbstractPages extends Composite implements Pages {
    protected final Listener<String> statusListener;
    protected final Listener<PageWidget> openListener;
    private Page currentPage;

    private final Event<String> onStatus;
    private final Listener<PageWidget> closeListener;
    private Page previouslyVisiblePage;

    public AbstractPages() {
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
		if (page.isOpened())
		    showPreviousPage();
		else
		    open(page);
	    }
	};

	closeListener = new Listener<PageWidget>() {
	    @Override
	    public void onEvent(PageWidget page) {
		closePage(page);
	    }
	};

    }

    /**
     * @see Pages
     */
    @Override
    public void add(Page page, Position position) {
	if (!hasPage(page)) {
	    page.onStatusChanged(statusListener);
	    page.onOpenChanged(openListener);
	    page.onClose(closeListener);
	    addPage(page, position);
	    if (position == Pages.Position.visible) {
		open(page);
	    } else if (position == Pages.Position.normal && currentPage != null) {
		open(currentPage);
	    }
	}
    }

    public Page getCurrentPage() {
	return currentPage;
    }

    /**
     * @see Pages
     */
    @Override
    public void open(Page page) {
	if (currentPage != page) {
	    this.previouslyVisiblePage = currentPage;
	    if (currentPage != null) {
		currentPage.setOpen(false);
	    }

	    if (!hasPage(page)) {
		add(page, Pages.Position.normal);
	    }

	    showPage(page);
	    currentPage = page;
	    page.setOpen(true);
	}
    }

    public void showPreviousPage() {
	GWT.log("PREVIOUS: " + previouslyVisiblePage, null);
	if (previouslyVisiblePage != null) {
	    GWT.log("SHOW PREVIOUS", null);
	    open(previouslyVisiblePage);
	}
    }

    private void closePage(PageWidget page) {
	if (page.isOpened()) {
	    GWT.log("CLOSE PAGE VISIBLE - Showing previous page...", null);
	    showPreviousPage();
	}
	hide(page);

    }

    protected abstract void addPage(Page page, Pages.Position position);

    protected abstract void showPage(Page page);
}
