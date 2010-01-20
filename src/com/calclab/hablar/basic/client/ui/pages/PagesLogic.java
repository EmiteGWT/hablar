package com.calclab.hablar.basic.client.ui.pages;

import java.util.ArrayList;

import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.hablar.basic.client.ui.page.PageWidget;
import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;
import com.calclab.suco.client.events.Event;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;

public class PagesLogic implements Pages {
    protected final Listener<PageWidget> statusListener;
    protected final Listener<PageWidget> visibilityChangedListener;
    private PageView currentPageView;

    private final Event<PageView> onStatus;
    private final Listener<PageWidget> closeListener;
    private PageView previouslyVisiblePageView;
    private final ArrayList<PageView> hidden;
    private final PagesPanel view;
    private int visiblePagesCount = 0;
    private final Event<PageView> onOpen;

    public PagesLogic(PagesPanel view) {
	this.view = view;
	this.onStatus = new Event<PageView>("pages.onStatus");
	this.onOpen = new Event<PageView>("pages.onOpen");
	this.hidden = new ArrayList<PageView>();

	statusListener = new Listener<PageWidget>() {
	    @Override
	    public void onEvent(PageWidget page) {
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
		hide(page);
	    }
	};
    }

    /**
     * @see Pages
     */
    public void add(PageView pageView) {
	if (!view.hasPageView(pageView)) {
	    pageView.onStatusMessageChanged(statusListener);
	    pageView.onVisibilityChanged(visibilityChangedListener);
	    pageView.onClose(closeListener);

	    Visibility visibility = pageView.getVisibility();
	    if (visibility == Visibility.open) {
		view.addPageView(pageView);
		visiblePagesCount++;
		open(pageView);
	    } else if (visibility == Visibility.hidden) {
		hidden.add(pageView);
	    } else if (visibility == Visibility.closed) {
		view.addPageView(pageView);
		visiblePagesCount++;
		if (currentPageView != null) {
		    open(currentPageView);
		}
	    }
	}
    }

    @Override
    public boolean close(PageView pageView) {
	if (pageView.getVisibility() == Visibility.open) {
	    pageView.setVisibility(Visibility.closed);
	    return true;
	} else {
	    return false;
	}
    }

    public PageView getCurrentPageView() {
	return currentPageView;
    }

    @Override
    public boolean hasPageView(PageView pageView) {
	return view.hasPageView(pageView);
    }

    /**
     * @see Pages
     */
    public void hide(PageView pageView) {
	if (pageView.getVisibility() == Visibility.open) {
	    showPreviousPage();
	}
	if (currentPageView == pageView) {
	    currentPageView = null;
	}
	if (previouslyVisiblePageView == pageView) {
	    previouslyVisiblePageView = null;
	}
	hidden.add(pageView);
	pageView.setVisibility(Visibility.hidden);
	view.removePageView(pageView);
    }

    @Override
    public void onPageOpened(Listener<PageView> listener) {
	onOpen.add(listener);
    }

    public void onStatusMessageChanged(Listener<PageView> listener) {
	onStatus.add(listener);
    }

    /**
     * @see Pages
     */
    public void open(PageView pageView) {
	boolean isHidden = hidden.contains(pageView);
	if (view.hasPageView(pageView) || isHidden) {
	    GWT.log("Show page (hidden " + isHidden + ")", null);
	    if (currentPageView != pageView) {
		this.previouslyVisiblePageView = currentPageView;
		if (currentPageView != null) {
		    currentPageView.setVisibility(Visibility.closed);
		}

		if (isHidden) {
		    GWT.log("ADDPAGE", null);
		    hidden.remove(pageView);
		    view.addPageView(pageView);
		}

		view.showPageView(pageView);
		currentPageView = pageView;
		pageView.setVisibility(Visibility.open);
	    }
	    onOpen.fire(pageView);
	}
    }

    public void showPreviousPage() {
	if (previouslyVisiblePageView != null) {
	    open(previouslyVisiblePageView);
	}
    }

}
