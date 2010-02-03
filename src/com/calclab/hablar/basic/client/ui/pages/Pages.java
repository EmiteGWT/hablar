package com.calclab.hablar.basic.client.ui.pages;

import java.util.List;

import com.calclab.hablar.basic.client.ui.page.PageView;

public interface Pages {

    void add(PageView pageView);

    /**
     * FIXME: just to work with docked.
     * 
     * @param pageView
     */
    void addGhost(PageView pageView);

    /**
     * Close the given page.
     * 
     * @param pageView
     *            the page to be closed
     * @return true if closed
     */
    boolean close(PageView pageView);

    List<PageView> getPagesOfType(String pageType);

    /**
     * 
     * @param pageView
     * @return
     */
    boolean hasPageView(PageView pageView);

    /**
     * Hide a page (do not show it in the pages ui).
     * 
     * @param pageView
     *            the page to be hidden
     */
    void hide(PageView pageView);

    /**
     * Open the given page. If the page is hidden, it whill be show.
     * 
     * @param pageView
     *            the page to be showed
     */
    void open(PageView pageView);
}
