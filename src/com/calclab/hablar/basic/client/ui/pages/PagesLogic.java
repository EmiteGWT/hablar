package com.calclab.hablar.basic.client.ui.pages;

import java.util.ArrayList;
import java.util.List;

import com.calclab.hablar.basic.client.HablarEventBus;
import com.calclab.hablar.basic.client.ui.page.PageLogic;
import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.hablar.basic.client.ui.page.PageView.Visibility;
import com.calclab.hablar.basic.client.ui.page.events.ClosePageEvent;
import com.calclab.hablar.basic.client.ui.page.events.ClosePageHandler;
import com.calclab.hablar.basic.client.ui.page.events.OpenPageEvent;
import com.calclab.hablar.basic.client.ui.page.events.OpenPageHandler;
import com.calclab.hablar.basic.client.ui.page.events.UserMessageEvent;
import com.calclab.hablar.basic.client.ui.page.events.UserMessageHandler;
import com.calclab.hablar.basic.client.ui.page.events.VisibilityChangedEvent;
import com.calclab.hablar.basic.client.ui.page.events.VisibilityChangedHandler;
import com.calclab.hablar.basic.client.ui.pages.events.PageClosedEvent;
import com.calclab.hablar.basic.client.ui.pages.events.PageOpenedEvent;
import com.google.gwt.core.client.GWT;

public class PagesLogic implements Pages {
    private PageView currentPageView;

    private PageView previouslyVisiblePageView;
    private final ArrayList<PageView> hiddenPages;
    private final PagesPanel view;
    private final ArrayList<PageView> visiblePages;
    private final HablarEventBus hablarEventBus;

    private PageView ghostPage;

    public PagesLogic(HablarEventBus hablarEventBus, PagesPanel view) {
	this.hablarEventBus = hablarEventBus;
	this.view = view;
	this.hiddenPages = new ArrayList<PageView>();
	this.visiblePages = new ArrayList<PageView>();

	hablarEventBus.addHandler(UserMessageEvent.TYPE, new UserMessageHandler() {
	    @Override
	    public void onUserMessage(UserMessageEvent event) {
		// whenStatus();
	    }
	});

	hablarEventBus.addHandler(VisibilityChangedEvent.TYPE, new VisibilityChangedHandler() {
	    @Override
	    public void onVisibilityChanged(VisibilityChangedEvent event) {
		whenVisibilityChanged(event.getPage());
	    }
	});

	hablarEventBus.addHandler(ClosePageEvent.TYPE, new ClosePageHandler() {
	    @Override
	    public void onPageClosed(PageLogic page) {
		whenPageClosed(page);
	    }
	});

	hablarEventBus.addHandler(OpenPageEvent.TYPE, new OpenPageHandler() {
	    @Override
	    public void onOpenPage(OpenPageEvent event) {
		open(event.getPage().getView());
	    }
	});

    }

    /**
     * @see Pages
     */
    public void add(PageView pageView) {
	if (!view.hasPageView(pageView)) {

	    Visibility visibility = pageView.getVisibility();
	    GWT.log("ADD: " + visibility, null);
	    if (visibility == Visibility.focused || (visibility == Visibility.notFocused) && visiblePages.size() == 0) {
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
    public void addGhost(PageView pageView) {
	ghostPage = pageView;
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

	if (pageType.equals(ghostPage.getPageType())) {
	    list.add(ghostPage);
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
	hablarEventBus.fireEvent(new PageClosedEvent(pageView));
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
	    hablarEventBus.fireEvent(new PageOpenedEvent(pageView));
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

    void whenPageClosed(PageLogic page) {
	hide(page.getView());
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
    }

    void whenVisibilityChanged(PageLogic page) {
	Visibility visibility = page.getVisibility();
	if (visibility == Visibility.focused)
	    showPreviousPage();
	else
	    open(page.getView());

    }

}
