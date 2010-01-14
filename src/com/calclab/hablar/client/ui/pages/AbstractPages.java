package com.calclab.hablar.client.ui.pages;

import java.util.ArrayList;

import com.calclab.hablar.client.ui.page.Page;
import com.calclab.hablar.client.ui.page.PageWidget;
import com.calclab.hablar.client.ui.page.Page.Visibility;
import com.calclab.suco.client.events.Event;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Composite;

public abstract class AbstractPages extends Composite implements Pages {
    protected final Listener<PageWidget> statusListener;
    protected final Listener<PageWidget> visibilityChangedListener;
    private Page currentPage;

    private final Event<Page> onStatus;
    private final Listener<PageWidget> closeListener;
    private Page previouslyVisiblePage;
    private final ArrayList<Page> hidden;

    public AbstractPages() {
	this.onStatus = new Event<Page>("pages.onStatus");
	this.hidden = new ArrayList<Page>();

	statusListener = new Listener<PageWidget>() {
	    @Override
	    public void onEvent(PageWidget page) {
		if (page.getVisibility() == Visibility.hidden) {
		    show(page);
		}
		onStatus.fire(page);
	    }
	};
	visibilityChangedListener = new Listener<PageWidget>() {
	    @Override
	    public void onEvent(PageWidget page) {
		if (page.getVisibility() == Visibility.open)
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
    public void add(Page page, Visibility visibility) {
	if (!hasPage(page)) {
	    page.onStatusMessageChanged(statusListener);
	    page.onVisibilityChanged(visibilityChangedListener);
	    page.onClose(closeListener);
	    addPage(page);
	    if (visibility == Visibility.open) {
		open(page);
	    } else if (visibility == Visibility.hidden) {
		hide(page);
	    } else if (currentPage != null) {
		open(currentPage);
	    }
	    page.setVisibility(visibility);
	}
    }

    public Page getCurrentPage() {
	return currentPage;
    }

    /**
     * @see Pages
     */
    @Override
    public void hide(Page page) {
	hidden.add(page);
	page.setVisibility(Visibility.hidden);
	removePage(page);
    }

    @Override
    public void onStatusMessageChanged(Listener<Page> listener) {
	onStatus.add(listener);
    }

    /**
     * @see Pages
     */
    @Override
    public void open(Page page) {
	if (currentPage != page) {
	    this.previouslyVisiblePage = currentPage;
	    if (currentPage != null) {
		currentPage.setVisibility(Visibility.closed);
	    }

	    show(page);

	    showPage(page);
	    currentPage = page;
	    page.setVisibility(Visibility.open);
	}
    }

    @Override
    public void show(Page page) {
	if (hidden.contains(page)) {
	    hidden.remove(page);
	    addPage(page);
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
	if (page.getVisibility() == Visibility.open) {
	    // show other page first (visible pages cannot be closed)
	    showPreviousPage();
	}
	hide(page);
    }

    protected abstract void addPage(Page page);

    protected abstract void removePage(Page page);

    protected abstract void showPage(Page page);
}
