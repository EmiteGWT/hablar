package com.calclab.hablar.client.ui.pages;

import java.util.ArrayList;

import com.calclab.hablar.client.ui.page.Page;
import com.calclab.hablar.client.ui.page.PageWidget;
import com.calclab.hablar.client.ui.page.Page.Visibility;
import com.calclab.suco.client.events.Event;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;

public class PagesLogic implements Pages {
    protected final Listener<PageWidget> statusListener;
    protected final Listener<PageWidget> visibilityChangedListener;
    private Page currentPage;

    private final Event<Page> onStatus;
    private final Listener<PageWidget> closeListener;
    private Page previouslyVisiblePage;
    private final ArrayList<Page> hidden;
    private final PagesPanel view;

    public PagesLogic(PagesPanel view) {
	this.view = view;
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
		Visibility visibility = page.getVisibility();
		if (visibility == Visibility.open)
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
    public void add(Page page) {
	if (!view.hasPage(page)) {
	    page.onStatusMessageChanged(statusListener);
	    page.onVisibilityChanged(visibilityChangedListener);
	    page.onClose(closeListener);

	    Visibility visibility = page.getVisibility();
	    if (visibility == Visibility.open) {
		view.addPage(page);
		open(page);
	    } else if (visibility == Visibility.hidden) {
		hidden.add(page);
	    } else if (visibility == Visibility.closed) {
		view.addPage(page);
		if (currentPage != null) {
		    open(currentPage);
		}
	    }
	}
    }

    public Page getCurrentPage() {
	return currentPage;
    }

    @Override
    public boolean hasPage(Page page) {
	return view.hasPage(page);
    }

    /**
     * @see Pages
     */
    public void hide(Page page) {
	hidden.add(page);
	page.setVisibility(Visibility.hidden);
	view.removePage(page);
    }

    public void onStatusMessageChanged(Listener<Page> listener) {
	onStatus.add(listener);
    }

    /**
     * @see Pages
     */
    public void open(Page page) {
	if (currentPage != page) {
	    this.previouslyVisiblePage = currentPage;
	    if (currentPage != null) {
		currentPage.setVisibility(Visibility.closed);
	    }

	    show(page);

	    view.showPage(page);
	    currentPage = page;
	    if (page.getVisibility() != Visibility.open)
		page.setVisibility(Visibility.open);
	}
    }

    public void show(Page page) {
	if (hidden.contains(page)) {
	    hidden.remove(page);
	    view.addPage(page);
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

}
