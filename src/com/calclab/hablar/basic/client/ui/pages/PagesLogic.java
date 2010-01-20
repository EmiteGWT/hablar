package com.calclab.hablar.basic.client.ui.pages;

import java.util.ArrayList;
import java.util.List;

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
    private final ArrayList<PageView> hiddenPages;
    private final PagesPanel view;
    private final Event<PageView> onOpened;
    private final Event<PageView> onClosed;
    private final ArrayList<PageView> visiblePages;

    public PagesLogic(PagesPanel view) {
	this.view = view;
	this.onStatus = new Event<PageView>("pages.onStatus");
	this.onOpened = new Event<PageView>("pages.onOpen");
	this.onClosed = new Event<PageView>("pages.onOpen");
	this.hiddenPages = new ArrayList<PageView>();
	this.visiblePages = new ArrayList<PageView>();

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
		visiblePages.add(pageView);
		open(pageView);
	    } else if (visibility == Visibility.hidden) {
		hiddenPages.add(pageView);
	    } else if (visibility == Visibility.closed) {
		view.addPageView(pageView);
		visiblePages.add(pageView);
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
    public List<PageView> getPagesOfType(String pageType) {
	ArrayList<PageView> list = new ArrayList<PageView>();

	for (PageView pageView : visiblePages) {
	    if (pageType.equals(pageView.getPageType())) {
		list.add(pageView);
	    }
	}
	for (PageView pageView : hiddenPages) {
	    if (pageType.equals(pageView.getPageType())) {
		list.add(pageView);
	    }
	}

	return list;
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
	hiddenPages.add(pageView);
	pageView.setVisibility(Visibility.hidden);
	view.removePageView(pageView);
	onClosed.fire(pageView);
    }

    @Override
    public void onPageClosed(Listener<PageView> listener) {
	onClosed.add(listener);
    }

    @Override
    public void onPageOpened(Listener<PageView> listener) {
	onOpened.add(listener);
    }

    public void onStatusMessageChanged(Listener<PageView> listener) {
	onStatus.add(listener);
    }

    /**
     * @see Pages
     */
    public void open(PageView pageView) {
	boolean isHidden = hiddenPages.contains(pageView);
	if (view.hasPageView(pageView) || isHidden) {
	    GWT.log("Show page (hidden " + isHidden + ")", null);
	    if (currentPageView != pageView) {
		this.previouslyVisiblePageView = currentPageView;
		if (currentPageView != null) {
		    currentPageView.setVisibility(Visibility.closed);
		}

		if (isHidden) {
		    GWT.log("ADDPAGE", null);
		    hiddenPages.remove(pageView);
		    view.addPageView(pageView);
		}

		view.showPageView(pageView);
		currentPageView = pageView;
		pageView.setVisibility(Visibility.open);
	    }
	    onOpened.fire(pageView);
	}
    }

    public void showPreviousPage() {
	if (previouslyVisiblePageView != null) {
	    open(previouslyVisiblePageView);
	}
    }

}
