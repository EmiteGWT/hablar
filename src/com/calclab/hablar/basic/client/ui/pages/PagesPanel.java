package com.calclab.hablar.basic.client.ui.pages;

import com.calclab.hablar.basic.client.ui.page.PageView;

/**
 * Lo que se supone que tiene que implementar un planel
 */
public interface PagesPanel {
    void addPageView(PageView pageView);

    boolean hasPageView(PageView pageView);

    void removePageView(PageView pageView);

    void showPageView(PageView pageView);

}
