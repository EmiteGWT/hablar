package com.calclab.hablar.client.ui.pages;

import com.calclab.suco.client.events.Event;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;

public abstract class AbstractPages extends Composite {
    public static enum Position {
	normal, visible, WEST
    }
    protected final Listener<String> statusListener;
    protected final Listener<PageWidget> openListener;
    private PageWidget currentPage;

    private final Event<String> onStatus;
    private final Listener<PageWidget> closeListener;
    private PageWidget previouslyVisiblePage;

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
		    openPage(page);
	    }
	};

	closeListener = new Listener<PageWidget>() {
	    @Override
	    public void onEvent(PageWidget page) {
		closePage(page);
	    }
	};

    }

    public void add(PageWidget page, Position position) {
	page.onStatusChanged(statusListener);
	page.onOpenChanged(openListener);
	page.onClose(closeListener);
	addPage(page, position);
	if (position == Position.visible) {
	    openPage(page);
	} else if (position == Position.normal && currentPage != null) {
	    openPage(currentPage);
	}
    }

    public PageWidget getCurrentPage() {
	return currentPage;
    }

    /**
     * Open the given page. The page is added to the container if needed.
     * 
     * @param page
     *            the page to be open
     */
    public void openPage(PageWidget page) {
	if (currentPage != page) {
	    this.previouslyVisiblePage = currentPage;
	    if (currentPage != null) {
		currentPage.setOpen(false);
	    }

	    showPage(page);
	    currentPage = page;
	    page.setOpen(true);
	}
    }

    public abstract void removePage(PageWidget page);

    public void showPreviousPage() {
	GWT.log("PREVIOUS: " + previouslyVisiblePage, null);
	if (previouslyVisiblePage != null) {
	    GWT.log("SHOW PREVIOUS", null);
	    openPage(previouslyVisiblePage);
	}
    }

    private void closePage(PageWidget page) {
	if (page.isOpened()) {
	    GWT.log("CLOSE PAGE VISIBLE - Showing previous page...", null);
	    showPreviousPage();
	}
	removePage(page);

    }

    protected abstract void addPage(PageWidget page, Position position);

    protected abstract void showPage(PageWidget page);
}
