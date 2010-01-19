package com.calclab.hablar.basic.client.ui.pages;

import java.util.ArrayList;

import com.calclab.hablar.basic.client.ui.page.PageView;
import com.calclab.hablar.basic.client.ui.pages.PagesPanel;

public class PagesPanelTester implements PagesPanel {

    private final ArrayList<PageView> pageViews;

    public PagesPanelTester() {
	this.pageViews = new ArrayList<PageView>();
    }

    @Override
    public void addPageView(PageView pageView) {
	pageViews.add(pageView);
    }

    @Override
    public boolean hasPageView(PageView pageView) {
	return pageViews.contains(pageView);
    }

    @Override
    public void removePageView(PageView pageView) {
	pageViews.remove(pageView);
    }

    @Override
    public void showPageView(PageView pageView) {

    }

}
