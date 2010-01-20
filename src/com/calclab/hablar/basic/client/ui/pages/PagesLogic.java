package com.calclab.hablar.basic.client.ui.pages;

import java.util.ArrayList;
import java.util.List;

import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;
import com.calclab.suco.client.events.Event;
import com.calclab.suco.client.events.Listener;
import com.google.gwt.core.client.GWT;

public class PagesLogic implements Pages {
    protected final Listener<PageView> statusListener;
    protected final Listener<PageView> visibilityChangedListener;
    private PageView currentPageView;

    private final Event<PageView> onStatus;
    private final Listener<PageView> closeListener;
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

	statusListener = new Listener<PageView>() {
	    @Override
	    public void onEvent(PageView page) {
		whenStatus(page);
	    }
	};
	visibilityChangedListener = new Listener<PageView>() {
	    @Override
	    public void onEvent(PageView page) {
		whenVisibilityChanged(page);
	    }
	};

	closeListener = new Listener<PageView>() {
	    @Override
	    public void onEvent(PageView page) {
		whenPageClosed(page);
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
	    GWT.log("ADD: " + visibility, null);
	    if (visibility == Visibility.focused || (visibility == visibility.notFocused) && visiblePages.size() == 0) {
		view.addPageView(pageView);
		visiblePages.add(pageView);
		open(pageView);
	    } else if (visibility == Visibility.closed) {
		hiddenPages.add(pageView);
	    } else if (visibility == Visibility.notFocused) {
		view.addPageView(pageView);
		visiblePages.add(pageView);
		reopenCurrentPage();
	    }
	}
    }

    @Override
    public boolean close(PageView pageView) {
	if (pageView.getVisibility() == Visibility.focused) {
	    pageView.setVisibility(Visibility.notFocused);
	    return true;
	} else if (pageView.getVisibility() == Visibility.closed) {
	    unhide(pageView);
	    pageView.setVisibility(Visibility.notFocused);
	    reopenCurrentPage();
	    return true;
	} else {
	    return false;

	}
    }

    public PageView getCurrentPageView() {
	return currentPageView;
    }

    public List<PageView> getHiddenPages() {
	return hiddenPages;
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

    public List<PageView> getVisiblePages() {
	return visiblePages;
    }

    @Override
    public boolean hasPageView(PageView pageView) {
	return view.hasPageView(pageView);
    }

    /**
     * @see Pages
     */
    public void hide(PageView pageView) {
	if (pageView.getVisibility() == Visibility.focused) {
	    showPreviousPage();
	}
	if (currentPageView == pageView) {
	    currentPageView = null;
	}
	if (previouslyVisiblePageView == pageView) {
	    previouslyVisiblePageView = null;
	}
	visiblePages.remove(pageView);
	hiddenPages.add(pageView);
	pageView.setVisibility(Visibility.closed);
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
	    if (currentPageView != pageView) {
		this.previouslyVisiblePageView = currentPageView;
		if (currentPageView != null) {
		    currentPageView.setVisibility(Visibility.notFocused);
		}

		if (isHidden) {
		    unhide(pageView);
		}

		view.showPageView(pageView);
		currentPageView = pageView;
		pageView.setVisibility(Visibility.focused);
	    }
	    GWT.log("OPEN OPEN", null);
	    onOpened.fire(pageView);
	}
    }

    public void showPreviousPage() {
	if (previouslyVisiblePageView != null) {
	    open(previouslyVisiblePageView);
	}
    }

    private void reopenCurrentPage() {
	if (currentPageView != null) {
	    open(currentPageView);
	}
    }

    private void unhide(PageView pageView) {
	hiddenPages.remove(pageView);
	visiblePages.add(pageView);
	view.addPageView(pageView);
    }

    void whenPageClosed(PageView page) {
	hide(page);
    }

    /**
     * Called when a page change its status
     * 
     * @param page
     */
    void whenStatus(PageView page) {
	if (page.getVisibility() == Visibility.closed) {
	    close(page);
	}
	onStatus.fire(page);
    }

    void whenVisibilityChanged(PageView page) {
	Visibility visibility = page.getVisibility();
	if (visibility == Visibility.focused)
	    showPreviousPage();
	else
	    open(page);

    }

}
