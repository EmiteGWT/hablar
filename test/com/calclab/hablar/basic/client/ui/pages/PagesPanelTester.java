package com.calclab.hablar.basic.client.ui.pages;

import java.util.ArrayList;

import com.calclab.hablar.basic.client.ui.page.Page;
import com.calclab.hablar.basic.client.ui.pages.PagesPanel;

public class PagesPanelTester implements PagesPanel {

    private final ArrayList<Page> pages;

    public PagesPanelTester() {
	this.pages = new ArrayList<Page>();
    }

    @Override
    public void addPage(Page page) {
	pages.add(page);
    }

    @Override
    public boolean hasPage(Page page) {
	return pages.contains(page);
    }

    @Override
    public void removePage(Page page) {
	pages.remove(page);
    }

    @Override
    public void showPage(Page page) {

    }

}
